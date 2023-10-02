package uy.edu.ort.obligatorio.pizzahurt.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FechaExpiracionValidator implements ConstraintValidator<FechaExpiracion, String> {

    @Override
    public void initialize(FechaExpiracion constraintAnnotation) {
    }

    @Override
    public boolean isValid(String fecha, ConstraintValidatorContext context) {
        if (fecha == null || fecha.isEmpty()) {
            return true;
        }

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            LocalDate fechaExpiracion = LocalDate.parse("01/" + fecha, formatter);


            LocalDate fechaActual = LocalDate.now();

            System.out.println("Fecha de expiration " + fecha);

            System.out.println("Fecha actual " + fechaActual);

            return !fechaExpiracion.isAfter(fechaActual);
        } catch (Exception e) {
            return false;
        }
    }
}
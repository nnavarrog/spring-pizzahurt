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

            String[] partes = fecha.split("/");
            int mes = Integer.parseInt(partes[0]);
            int anio = Integer.parseInt(partes[1]);

            int anioCompleto = 2000 + anio;

            LocalDate fechaExpiracion = LocalDate.of(anioCompleto, mes, 1);

            LocalDate fechaActual = LocalDate.now();


            return fechaExpiracion.isAfter(fechaActual);
        } catch (Exception e) {
            return false;
        }
    }
}
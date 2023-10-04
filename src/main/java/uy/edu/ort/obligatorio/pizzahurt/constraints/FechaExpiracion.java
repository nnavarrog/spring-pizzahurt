package uy.edu.ort.obligatorio.pizzahurt.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FechaExpiracionValidator.class)
public @interface FechaExpiracion {
    String message() default "La fecha de vencimiento debe ser una fecha futura en formato MM/YY";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
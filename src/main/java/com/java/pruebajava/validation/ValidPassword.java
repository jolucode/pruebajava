package com.java.pruebajava.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Formato de contraseña inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
package ru.tyshchenko.springexamples.annotation;


import java.lang.annotation.Retention;
import javax.validation.Constraint;
import javax.validation.Payload;

import ru.tyshchenko.springexamples.validator.HasMoreThanOneValidator;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = {HasMoreThanOneValidator.class})
public @interface HasMoreThanOneLetter {

  String message() default "Invalid Limit of Code";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}

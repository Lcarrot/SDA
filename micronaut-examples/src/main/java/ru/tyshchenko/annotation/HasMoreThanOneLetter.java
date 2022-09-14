package ru.tyshchenko.annotation;


import java.lang.annotation.Retention;
import javax.validation.Constraint;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = { })
public @interface HasMoreThanOneLetter {

  String message() default "invalid duration";
}

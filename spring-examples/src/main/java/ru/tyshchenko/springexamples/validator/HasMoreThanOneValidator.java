package ru.tyshchenko.springexamples.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import ru.tyshchenko.springexamples.annotation.HasMoreThanOneLetter;

@Component
public class HasMoreThanOneValidator
    implements ConstraintValidator<HasMoreThanOneLetter, String> {

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    return s == null || s.length() > 1;
  }
}

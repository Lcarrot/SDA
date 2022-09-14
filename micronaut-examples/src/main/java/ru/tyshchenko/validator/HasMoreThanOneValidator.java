package ru.tyshchenko.validator;

import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext;
import jakarta.inject.Singleton;
import ru.tyshchenko.annotation.HasMoreThanOneLetter;

@Singleton
public class HasMoreThanOneValidator
    implements ConstraintValidator<HasMoreThanOneLetter, CharSequence> {

  @Override
  public boolean isValid(CharSequence value,
      AnnotationValue<HasMoreThanOneLetter> annotationMetadata,
      ConstraintValidatorContext context) {
    context.messageTemplate(annotationMetadata.get("message", String.class,
        "invalid duration")); //
    return value == null || value.length() > 1;
  }
}

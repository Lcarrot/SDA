package ru.tyshchenko.converter;

import java.util.Optional;

import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.beans.BeanProperty;
import jakarta.inject.Singleton;

@Singleton
public class FieldConverter {

  public <T, F> T convertFromObject(F from, Class<T> toClass) {
    BeanIntrospection<F> fromIntrospection =
        (BeanIntrospection<F>) BeanIntrospection.getIntrospection(from.getClass());
    BeanIntrospection<T> toIntrospection = BeanIntrospection.getIntrospection(toClass);
    T result = toIntrospection.instantiate();
    String[] fieldNames = fromIntrospection.getPropertyNames();
    for (String fieldName : fieldNames) {
      BeanProperty fromBeanProperty = fromIntrospection.getProperty(fieldName).get();
      Optional<BeanProperty> toBeanPropertyOp = toIntrospection.getProperty(
          fromBeanProperty.getName(), fromBeanProperty.getType());
      if (toBeanPropertyOp.isPresent()) {
        BeanProperty toBeanProperty = toBeanPropertyOp.get();
        toBeanProperty.set(result, fromBeanProperty.get(from));
      }
    }
    return result;
  }
}

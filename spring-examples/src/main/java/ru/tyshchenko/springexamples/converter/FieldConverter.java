package ru.tyshchenko.springexamples.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FieldConverter {

  public <T, F> T convertFromObject(F from, Class<T> toClass) {
    T value = BeanUtils.instantiateClass(toClass);
    BeanUtils.copyProperties(from, value);
    return value;
  }
}

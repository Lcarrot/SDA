package ru.tyshchenko.springexamples.generic;

public class GenericValueBox<T> {

  private final T value;

  public GenericValueBox(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }
}

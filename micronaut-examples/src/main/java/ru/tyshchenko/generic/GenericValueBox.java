package ru.tyshchenko.generic;

public class GenericValueBox<T> {

  private final T value;

  public GenericValueBox(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }
}

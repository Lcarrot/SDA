package ru.tyshchenko;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tyshchenko.dto.ShopDto;
import ru.tyshchenko.generic.GenericValueBox;

@MicronautTest
public class GenericTest {

  @Inject
  private GenericValueBox<ShopDto> valueBox;

  @Test
  public void testGeneric() {
    Assertions.assertEquals(ShopDto.class, valueBox.getValue().getClass());
  }
}

package ru.tyshchenko.springexamples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ResolvableType;
import ru.tyshchenko.springexamples.dto.ShopDto;
import ru.tyshchenko.springexamples.generic.GenericValueBox;

@SpringBootTest
public class GenericTest {

  @Autowired
  private GenericValueBox<ShopDto> valueBox;

  @Test
  public void testGeneric() {
    Assertions.assertEquals(ShopDto.class, valueBox.getValue().getClass());
  }

  @Test
  public void testGetGenericType() throws NoSuchFieldException {
    ResolvableType t1 = ResolvableType.forField(getClass().getDeclaredField("valueBox"));
    ResolvableType t2 = t1.getGeneric();
    Assertions.assertEquals(ShopDto.class.getTypeName(), t2.getType().getTypeName());
  }
}

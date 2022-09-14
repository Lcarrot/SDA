package ru.tyshchenko.springexamples;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.tyshchenko.springexamples.model.Shop;
import ru.tyshchenko.springexamples.repository.ShopRepository;
import ru.tyshchenko.springexamples.services.ShopService;

@SpringBootTest
public class PersonServiceTest {

  @Autowired
  private ShopService shopService;

  @MockBean
  private ShopRepository repository;

  @BeforeEach
  void configure() {
    Mockito.when(repository.findByName("vmk")).thenReturn(new Shop(2L, "vmk", null));
  }

  @Test
  void testNotNullThrowException() {
    Assertions.assertThrows(IllegalStateException.class, () -> shopService.getShopOwner("vmk"));
  }

  //didn't work in spring
  @Test
  void testOneLetterInArgument() {
    Throwable throwable = Assertions.assertThrows(ConstraintViolationException.class,
        () -> shopService.getShop("name", "a")
    );
    Assertions.assertEquals("getShop.owner: invalid duration", throwable.getMessage());
  }
}

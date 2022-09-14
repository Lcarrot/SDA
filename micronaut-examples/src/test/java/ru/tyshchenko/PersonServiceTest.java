package ru.tyshchenko;

import javax.validation.ConstraintViolationException;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.tyshchenko.model.Shop;
import ru.tyshchenko.repository.ShopRepository;
import ru.tyshchenko.services.ShopService;
import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.mock;

@MicronautTest
public class PersonServiceTest {

  @Inject
  private ShopService shopService;

  @MockBean
  @Replaces(ShopRepository.class)
  ShopRepository mockRepo() {
    ShopRepository repository = mock(ShopRepository.class);
    Mockito.when(repository.find("vmk")).thenReturn(new Shop(2L, "vmk", null));
    return repository;
  }

  @Test
  void testNotNullThrowException() {
    Assertions.assertThrows(IllegalStateException.class, () -> shopService.getShopOwner("vmk"));
  }

  @Test
  void testOneLetterInArgument() {
    Throwable throwable = Assertions.assertThrows(ConstraintViolationException.class,
        () -> shopService.getShop("name", "a")
    );
    Assertions.assertEquals("getShop.owner: invalid duration", throwable.getMessage());
  }
}

package ru.tyshchenko.factory;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import ru.tyshchenko.dto.ShopDto;
import ru.tyshchenko.generic.GenericValueBox;
import ru.tyshchenko.model.Shop;

@Factory
public class GenericBoxFactory {

  @Bean
  GenericValueBox<ShopDto> shopDtoGeneric() {
    return new GenericValueBox<>(new ShopDto());
  }

  @Bean
  GenericValueBox<Shop> shopGeneric() {
    return new GenericValueBox<>(new Shop());
  }
}

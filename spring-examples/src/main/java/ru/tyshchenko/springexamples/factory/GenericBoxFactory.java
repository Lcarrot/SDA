package ru.tyshchenko.springexamples.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tyshchenko.springexamples.dto.ShopDto;
import ru.tyshchenko.springexamples.generic.GenericValueBox;
import ru.tyshchenko.springexamples.model.Shop;

@Configuration
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

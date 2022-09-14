package ru.tyshchenko.springexamples.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.tyshchenko.springexamples.annotation.HasMoreThanOneLetter;
import ru.tyshchenko.springexamples.annotation.NotNullReturning;
import ru.tyshchenko.springexamples.converter.FieldConverter;
import ru.tyshchenko.springexamples.dto.ShopDto;
import ru.tyshchenko.springexamples.repository.ShopRepository;

@Service
public class ShopService {

  private static final Logger logger = LoggerFactory.getLogger(ShopService.class);

  private final ShopRepository shopRepository;
  private final FieldConverter fieldConverter;

  @PostConstruct
  public void start() {
    logger.info("i'm started! \uD83D\uDE00");
  }

  public ShopService(ShopRepository shopRepository,
      FieldConverter fieldConverter) {
    this.shopRepository = shopRepository;
    this.fieldConverter = fieldConverter;
  }

  @NotNullReturning
  public String getShopOwner(@NotNull String name) {
    return fieldConverter.convertFromObject(shopRepository.findByName(name), ShopDto.class).getOwner();
  }

  public ShopDto getShop(@NotNull String name, @HasMoreThanOneLetter String owner) {
    return fieldConverter.convertFromObject(shopRepository.getByNameAndOwner(name, owner),
        ShopDto.class
    );
  }

  @PreDestroy
  public void destroy() {
    logger.info("I'm destroyed \uD83D\uDE15");
  }
}

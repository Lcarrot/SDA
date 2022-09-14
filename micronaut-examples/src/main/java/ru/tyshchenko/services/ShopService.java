package ru.tyshchenko.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tyshchenko.annotation.HasMoreThanOneLetter;
import ru.tyshchenko.annotation.NotNullReturning;
import ru.tyshchenko.annotation.Service;
import ru.tyshchenko.converter.FieldConverter;
import ru.tyshchenko.dto.ShopDto;
import ru.tyshchenko.repository.ShopRepository;

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
    return fieldConverter.convertFromObject(shopRepository.find(name), ShopDto.class).getOwner();
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

package ru.tyshchenko.controller;

import javax.validation.Valid;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tyshchenko.dto.ShopDto;
import ru.tyshchenko.services.ShopService;

@Controller
public class ShopController {

  private static final Logger logger = LoggerFactory.getLogger(ShopService.class);

  @Inject
  private ShopService shopService;

  @Get("/owner")
  public String getShopOwner(String name) {
    return shopService.getShopOwner(name);
  }

  @Get("/shopInfo")
  public ShopDto getShopByNameAndOwner(String name, String owner) {
    return shopService.getShop(name, owner);
  }

  @Post("/save")
  HttpResponse<String> addUser(@Valid @Body ShopDto user) {
    logger.info(user.toString());
    return HttpResponse.ok("User is valid");
  }
}

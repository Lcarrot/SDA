package ru.tyshchenko.springexamples.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tyshchenko.springexamples.dto.ShopDto;
import ru.tyshchenko.springexamples.services.ShopService;

@RestController
public class ShopController {

  private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

  @Autowired
  private ShopService shopService;

  @GetMapping("/owner")
  public String getShopOwner(String name) {
    return shopService.getShopOwner(name);
  }

  @GetMapping("/shopInfo")
  public ShopDto getShopByNameAndOwner(String name, String owner) {
    return shopService.getShop(name, owner);
  }

  @PostMapping("/save")
  ResponseEntity<String> addUser(@Valid @RequestBody ShopDto user) {
    logger.info(user.toString());
    return ResponseEntity.ok("User is valid");
  }
}

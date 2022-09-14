package ru.tyshchenko.dto;

import javax.validation.Valid;

import io.micronaut.core.annotation.Introspected;
import ru.tyshchenko.annotation.HasMoreThanOneLetter;

@Introspected
public class ShopDto {

  private Long id;
  @HasMoreThanOneLetter
  private String name;
  private String owner;

  @Valid
  public ShopDto(Long id, String name, String owner) {
    this.id = id;
    this.name = name;
    this.owner = owner;
  }

  public ShopDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }
}

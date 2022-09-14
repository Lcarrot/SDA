package ru.tyshchenko.repository;

import javax.sql.DataSource;

import io.micronaut.context.annotation.Executable;
import io.micronaut.context.annotation.Requires;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import ru.tyshchenko.model.Shop;

@Repository
@Requires(bean = DataSource.class, sdk = Requires.Sdk.JAVA)
public interface ShopRepository {

  @Executable
  Shop find(String name);

  @Executable
  @Query("select shop from Shop as shop where shop.name=:name and shop.owner=:owner")
  Shop getShop(String name, String owner);

  @Executable
  Shop getByNameAndOwner(String name, String owner);
}

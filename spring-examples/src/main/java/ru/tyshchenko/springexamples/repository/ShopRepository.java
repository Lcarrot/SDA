package ru.tyshchenko.springexamples.repository;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tyshchenko.springexamples.model.Shop;

//@ConditionalOnBean(DataSource.class)
public interface ShopRepository extends JpaRepository<Shop, Long> {

  Shop findByName(String name);

  @Query("select shop from Shop as shop where shop.name=:name and shop.owner=:owner")
  Shop getShop(String name, String owner);

  Shop getByNameAndOwner(String name, String owner);
}

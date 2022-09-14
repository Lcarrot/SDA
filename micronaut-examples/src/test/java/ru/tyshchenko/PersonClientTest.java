package ru.tyshchenko;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.tyshchenko.dto.ShopDto;
import ru.tyshchenko.model.Shop;
import ru.tyshchenko.repository.ShopRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@MicronautTest
public class PersonClientTest {

  @Inject
  @Client("/")
  private HttpClient client;

  @MockBean
  @Replaces(ShopRepository.class)
  ShopRepository mockRepo() {
    ShopRepository repository = mock(ShopRepository.class);
    Mockito.when(repository.find("itis"))
        .thenReturn(new Shop(1L, "itis", "Abramskiy"));
    Mockito.when(repository.getByNameAndOwner("vmk", "me"))
        .thenReturn(new Shop(2L, "vmk", "me"));
    return repository;
  }

  @Test
  void testGetShopInfo() {
    String response = client.toBlocking()
        .retrieve(HttpRequest.GET("/owner?name=itis"));
    assertEquals("Abramskiy", response);
  }

  @Test
  void testShopByNameAndOwner() {
    String response = client.toBlocking()
        .retrieve(HttpRequest.GET("/shopInfo?name=vmk&owner=me"));
    assertEquals("{\"id\":2,\"name\":\"vmk\",\"owner\":\"me\"}", response);
  }

  @Test
  void testSaveNotValidClient() {
    assertThrows(HttpClientResponseException.class, () -> client.toBlocking()
        .retrieve(HttpRequest.POST("/save", new ShopDto(2L, "a", "me"))));
  }
}

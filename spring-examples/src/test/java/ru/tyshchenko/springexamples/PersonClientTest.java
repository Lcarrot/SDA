package ru.tyshchenko.springexamples;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.tyshchenko.springexamples.dto.ShopDto;
import ru.tyshchenko.springexamples.model.Shop;
import ru.tyshchenko.springexamples.repository.ShopRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PersonClientTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ShopRepository repository;

  private final String serverUrl = "http://localhost:8080";

  @BeforeEach
  void configure() {
    Mockito.when(repository.findByName("itis"))
        .thenReturn(new Shop(1L, "itis", "Abramskiy"));
    Mockito.when(repository.getByNameAndOwner("vmk", "me"))
        .thenReturn(new Shop(2L, "vmk", "me"));
  }

  @Test
  void testGetShopInfo() throws Exception {
    mockMvc.perform(get("/owner")
            .param("name", "itis"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void testShopByNameAndOwner() throws Exception {
    mockMvc.perform(get("/shopInfo")
            .param("name", "vmk")
            .param("owner", "me"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void testSaveNotValidClient() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/save")
            .content(objectMapper.writeValueAsString(new ShopDto(2L, "a", "me")))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }
}

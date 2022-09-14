package ru.tyshchenko.springexamples;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringExamplesApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringExamplesApplication.class, args);
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}

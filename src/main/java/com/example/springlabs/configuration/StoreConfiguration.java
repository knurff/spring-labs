package com.example.springlabs.configuration;


import com.example.springlabs.model.Category;
import com.example.springlabs.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreConfiguration {

    final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${path.data}")
    String pathData;

    @Value("${path.users}")
    String pathUsers;

    @SneakyThrows
    @Bean
    List<Category> categories() {
        try (InputStream inputStream = new FileInputStream(pathData)) {
            return objectMapper.readValue(inputStream, new TypeReference<>() {});
        }
    }

    @SneakyThrows
    @Bean
    List<User> users() {
        try (InputStream inputStream = new FileInputStream(pathUsers)) {
            return objectMapper.readValue(inputStream, new TypeReference<>() {});
        }
    }
}

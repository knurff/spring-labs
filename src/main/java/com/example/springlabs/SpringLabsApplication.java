package com.example.springlabs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
        info = @Info(
                title = "Digital catalog of goods API", version = "1.0.0",
                description = "Documentation for the REST API of the electronic product catalogue"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Local Development Server"
                )
        }
)
@SpringBootApplication
public class SpringLabsApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringLabsApplication.class, args);
  }

}

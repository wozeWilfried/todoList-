package com.cwa.Todo_List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "TodoList API",
				version = "1.0.0",
				description = "Professional TodoList REST API with Spring Boot, JPA and MySQL",
				contact = @Contact(
						name = "Woze Wilfried",
						url = "https://github.com/wozeWilfried"
				),
				license = @License(
						name = "MIT License",
						url = "https://opensource.org/licenses/MIT"
				)
		)
)

public class TodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

}

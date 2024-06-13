package com.example.taskmanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
		info = @Info(
				title = "Task Manager",
				version = "1.0.0",
				description = "API documentation for the Task Manager application"
		)
)
public class OpenApiConfig {

}

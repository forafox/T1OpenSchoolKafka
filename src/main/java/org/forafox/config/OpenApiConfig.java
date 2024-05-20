package org.forafox.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "T1 Open School AOP",
                description = "Sample API of the AOP Test", version = "1.0.0",
                contact = @Contact(
                        name = "Karabanov Andrey",
                        email = "deskpa@yandex.ru"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Default Server URL"),
        },
        tags = {
                @Tag(name = "Film API", description = "API for films"),
                @Tag(name = "Methods Data API", description = "API for methods data")
        }
)
public class OpenApiConfig {

}
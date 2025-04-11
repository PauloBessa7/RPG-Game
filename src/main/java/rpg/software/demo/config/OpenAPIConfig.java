package rpg.software.demo.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API Exemplo",
        version = "v1",
        description = "Documentação da API Exemplo usando OpenAPI"
    )
)

public class OpenAPIConfig {
    // Esta classe é opcional, você pode não precisar dela
}
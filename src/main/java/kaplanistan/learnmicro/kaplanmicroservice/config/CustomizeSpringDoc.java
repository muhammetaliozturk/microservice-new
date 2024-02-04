package kaplanistan.learnmicro.kaplanmicroservice.config;

import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomizeSpringDoc {
    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("add-header")
                .addOperationCustomizer((operation, $) -> {
                    operation.addParametersItem(
                            new HeaderParameter()
                                    .name("Accept")
                                    .description("Accept Item")
                    );
                    return operation;
                })
                .build();
    }
}

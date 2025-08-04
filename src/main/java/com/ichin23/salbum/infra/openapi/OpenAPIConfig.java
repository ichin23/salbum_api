package com.ichin23.salbum.infra.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("REST API's RESTful from 0 with Java, SpringBoot, Kubernets and Docker")
                        .version("v1")
                        .description("REST API's RESTful from 0 with Java, SpringBoot, Kubernets and Docker")
                        .license( new License()
                                .name("Apache 2.0")
                                .url("https://github.com/ichin23")
                        )
                );
    }
}

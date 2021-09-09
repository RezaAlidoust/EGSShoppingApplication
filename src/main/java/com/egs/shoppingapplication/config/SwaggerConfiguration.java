package com.egs.shoppingapplication.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDescription, @Value("${application-version}") String appVersion, @Value("${spring.application.name}") String appName) {
        String securitySchemeName = "Auth JWT";
        return (new OpenAPI())
                .addSecurityItem((new SecurityRequirement()).addList(securitySchemeName))
                .info(new Info()
                        .title(appName)
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .components(
                        (new Components())
                                .addSecuritySchemes(securitySchemeName,
                                        (new SecurityScheme())
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}

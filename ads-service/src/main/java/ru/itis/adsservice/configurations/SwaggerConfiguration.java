package ru.itis.adsservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket adsApi() {
        return new Docket(DocumentationType.SWAGGER_2).
                select()
                .apis(RequestHandlerSelectors.basePackage("ru.itis.adsservice.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(swaggerApiInfo());
    }

    /**
     * Swagger meta-info for ads service API
     * */
    private ApiInfo swaggerApiInfo() {
        return new ApiInfo(
                "ADS SERVICE REST API",
                "Simple documentation for Ads service",
                "0.1",
                "Terms of service",
                new Contact(
                        "NDA Command",
                        "https://github.com/Sheventon/NDA",
                        "tores.fernando.real@gmail.com"
                ),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }
}

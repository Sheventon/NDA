package ru.itis.usersservice.configurations;

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

/**
 * SwaggerConfiguration
 * created: 12-07-2021 - 13:53
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Swagger configuration:
     *
     * base package for swagger api: "ru.itis.userservice.controllers.*"
     * all methods  are used to filter the controllers and methods that
     * are being documented using String predicates.
     * */

    @Bean
    public Docket usersApi() {
        return new Docket(DocumentationType.SWAGGER_2).
                select()
                .apis(RequestHandlerSelectors.basePackage("ru.itis.usersservice.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(swaggerApiInfo());
    }

    /**
     * Swagger meta-info for user service API
     * */
    private ApiInfo swaggerApiInfo() {
        return new ApiInfo(
                "USER SERVICE REST API",
                "Simple documentation for User service",
                "0.1",
                "Terms of service",
                new Contact(
                        "NDA Command",
                        "https://github.com/Sheventon/NDA",
                        "dinar.shagaliev2002@gmail.com"
                ),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }

}

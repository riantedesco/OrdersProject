package com.compass.msorder.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.compass.msorder.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageGetAndDelete())
                .globalResponseMessage(RequestMethod.POST, responseMessagePostAndPut())
                .globalResponseMessage(RequestMethod.PUT, responseMessagePostAndPut())
                .globalResponseMessage(RequestMethod.DELETE, responseMessageGetAndDelete())
                .apiInfo(apiInfo());
    }

    private List<ResponseMessage> responseMessageGetAndDelete() {
        return new ArrayList<ResponseMessage>() {
            {
                add(new ResponseMessageBuilder()
                        .code(400)
                        .message("Bad request")
                        .build());
            }
        };
    }

    private List<ResponseMessage> responseMessagePostAndPut() {
        return new ArrayList<ResponseMessage>() {
            {
                add(new ResponseMessageBuilder()
                        .code(400)
                        .message("Bad request")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(500)
                        .message("Campo nulo informado")
                        .build());
            }
        };
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("OrdersProject - API Msorder")
                .description("Documentação do msorder do projeto OrdersProject")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Rian Tedesco", null, "rian.tedesco@compasso.com.br"))
                .build();
    }
}

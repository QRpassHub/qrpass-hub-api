package com.porseacaso.qrpasshubapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerAPIConfig {
    @Value("${qrpass.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenApi(){

        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Development Server");

        Contact contact = new Contact();
        contact.setName("Porseacaso");
        contact.setEmail("porseacaso@gmail.com");

        License license = new License().name("MIT License").url("https://opensource.org/licenses/MIT");

        Info info = new Info()
                .title("QR Pass Hub API")
                .version("0.1.0")
                .contact(contact)
                .description("API for QR Pass Hub")
                .license(license);

        return new OpenAPI()
                .info(info)
                .addServersItem(devServer);
    }
}

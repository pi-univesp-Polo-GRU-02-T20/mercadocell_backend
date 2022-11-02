package br.com.univesp.mercadocell.mercadocell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MercadoCellApplication {

    public static String APPLICATION_SHORT_NAME = "Mercadocell";
    public static String APPLICATION_NAME = "Mercadocell API";
    public static void main(String[] args) {
        SpringApplication.run(MercadoCellApplication.class, args);
    }
}

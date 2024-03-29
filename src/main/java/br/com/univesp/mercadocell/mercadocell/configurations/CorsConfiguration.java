package br.com.univesp.mercadocell.mercadocell.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("Authorization")
                      //  .allowCredentials( true )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");//, ,  "HEAD", "TRACE", "CONNECT"
            }
        };
    }
}
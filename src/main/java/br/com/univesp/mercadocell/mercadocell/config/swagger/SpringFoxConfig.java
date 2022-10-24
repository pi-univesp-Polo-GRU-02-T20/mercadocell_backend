package br.com.univesp.mercadocell.mercadocell.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("MercadoCell API")
                        .build())
    //          .securitySchemes(Arrays.asList(securityScheme()))
                .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }

    private SecurityScheme securityScheme(){
        return new OAuthBuilder()
                .name("Mercadocell")
                .grantTypes(grantTypes())
                .scopes(scopes())
                .build();
    }
    private List<GrantType> grantTypes(){
        return Arrays.asList(new ResourceOwnerPasswordCredentialsGrant("/oauth/token"));
    }

    private List<AuthorizationScope> scopes(){
        return Arrays.asList(new AuthorizationScope("READ", "Acesso de leitura"),
                new AuthorizationScope("WRITE", "Acesso de edição")
        );
    }
}
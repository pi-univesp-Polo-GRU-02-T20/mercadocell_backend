package br.com.univesp.mercadocell.mercadocell.config.swagger;

import br.com.univesp.mercadocell.mercadocell.MercadoCellApplication;
import br.com.univesp.mercadocell.mercadocell.security.SecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.service.contexts.SecurityContext;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;



import static io.swagger.models.auth.In.HEADER;
import static java.util.Collections.singletonList;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.*;

@Configuration
public class SpringFoxConfig {
    public static final String AUTHORIZATION_HEADER = "authorization";
    public static final String AUTHENTICATION_TYPE = "JWT";
  //  public static final String HEADER = "header";

    private static final Set<String> DEFAULT_PRODUCES_CONSUMES = new HashSet<>(Arrays.asList("application/json"));


    @Bean
    public Docket docket() {

        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("Authorization")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .description("JWT token")
                .required(true)
                .build();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(parameterBuilder.build());



        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title(MercadoCellApplication.APPLICATION_NAME)
                        .build())
                //.securitySchemes(Arrays.asList(securityScheme()))
                //.securityContexts(Arrays.asList(securityContext()))
                .produces(DEFAULT_PRODUCES_CONSUMES)
                .consumes(DEFAULT_PRODUCES_CONSUMES)

                /*.securitySchemes(singletonList(new ApiKey("JWT", AUTHORIZATION_HEADER, HEADER.name())))
                .securityContexts(singletonList(
                        SecurityContext.builder()
                                .securityReferences(
                                        singletonList(SecurityReference.builder()
                                                .reference("authorization")
                                                .scopes(new AuthorizationScope[0])
                                                .build()
                                        )
                                )
                                .build())
                )


                 */
                .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                // Setting globalOperationParameters ensures that authentication header is applied to all APIs
                .globalOperationParameters(parameters);
    }

    private SecurityScheme securityScheme(){
        return new OAuthBuilder()
                .name(MercadoCellApplication.APPLICATION_SHORT_NAME)
                .grantTypes(grantTypes())
                .scopes(scopes())
                .build();
    }
    private List<GrantType> grantTypes(){
        return Arrays.asList(new ResourceOwnerPasswordCredentialsGrant(SecurityConfig.AUTHENTICATION_PATH));
    }

    private List<AuthorizationScope> scopes(){
        return Arrays.asList(new AuthorizationScope(SecurityConfig.AUTHORIZATION_SCOPE_READ, SecurityConfig.AUTHORIZATION_SCOPE_READ_DESCRIPTION),
                new AuthorizationScope(SecurityConfig.AUTHORIZATION_SCOPE_WRITE, SecurityConfig.AUTHORIZATION_SCOPE_WRITE_DESCRIPTION)
        );
    }
    private SecurityContext securityContext() {
        var securityReference = SecurityReference.builder()
                .reference(MercadoCellApplication.APPLICATION_SHORT_NAME)
                .scopes(scopes().toArray(new AuthorizationScope[0]))
                .build();

        return SecurityContext.builder()
                .securityReferences(Arrays.asList(securityReference))
                .forPaths(PathSelectors.any())
                .build();
    }

    private ApiKey securityScheme2() {
        return new ApiKey(AUTHORIZATION_HEADER, AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext2() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Keyname" , "header");
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference(AUTHENTICATION_TYPE, authorizationScopes));
    }
}
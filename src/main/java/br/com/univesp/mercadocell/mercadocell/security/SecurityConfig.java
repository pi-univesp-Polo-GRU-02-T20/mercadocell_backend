package br.com.univesp.mercadocell.mercadocell.security;

import br.com.univesp.mercadocell.mercadocell.service.DetalheUsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DetalheUsuarioServiceImpl detalheUsuarioService;

    public static final String AUTHENTICATION_PATH = "/login";
    public static final String AUTHORIZATION_SCOPE_READ = "READ";
    public static final String AUTHORIZATION_SCOPE_READ_DESCRIPTION = "Acesso de leitura";
    public static final String AUTHORIZATION_SCOPE_WRITE = "WRITE";
    public static final String AUTHORIZATION_SCOPE_WRITE_DESCRIPTION = "Acesso de gravação";
    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/catalogo-produto/**"
            // other public endpoints of your API may be appended to this array
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detalheUsuarioService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, AUTHENTICATION_PATH).permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .addFilter(new JWTAutenticacaoFilter(authenticationManager()))
                .addFilter(new JWTValidacaoFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}

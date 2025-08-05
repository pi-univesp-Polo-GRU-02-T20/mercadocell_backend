package br.com.univesp.mercadocell.mercadocell.security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors().disable()
            .authorizeRequests()
                .antMatchers("/**").permitAll() // Permitir acesso a todas as rotas
                .anyRequest().permitAll(); // Permitir qualquer requisição
    }

    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        // Ignorar autenticação para todas as rotas
        web.ignoring().antMatchers("/**");
    }
}
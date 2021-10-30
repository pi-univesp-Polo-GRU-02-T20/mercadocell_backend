package br.com.univesp.mercadocell.mercadocell.security;

import br.com.univesp.mercadocell.mercadocell.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Encoder {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
/*
    public static void main(String[] args) {
        PasswordEncoder psEncoder = new BCryptPasswordEncoder();
        String senha = "1234";
        String senhaEnc = psEncoder.encode(senha);

        boolean valido = psEncoder.matches(senha, senhaEnc);
        System.out.println(valido);
    }
 */
}


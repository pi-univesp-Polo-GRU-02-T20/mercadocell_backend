package br.com.univesp.mercadocell.mercadocell.security;

import br.com.univesp.mercadocell.mercadocell.dto.ResponseTokenJWTDTO;
import br.com.univesp.mercadocell.mercadocell.dto.UsuarioDTOLogin;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAutenticacaoFilter extends UsernamePasswordAuthenticationFilter {
    public static final int TOKEN_EXPIRACAO = 1_800_000; //30 min;
    public static final String TOKEN_SENHA = "630bcc5d-177b-40f2-94cd-5f4773157f08";

    private AuthenticationManager authenticationManager;

    public JWTAutenticacaoFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UsuarioDTOLogin usuarioDTOLogin = new ObjectMapper()
                    .readValue(request.getInputStream(), UsuarioDTOLogin.class);

            UsernamePasswordAuthenticationToken tokenUser = new UsernamePasswordAuthenticationToken(
                    usuarioDTOLogin.getLogin(),
                    usuarioDTOLogin.getSenha(),
                    new ArrayList<>()
            );
            return authenticationManager.authenticate(tokenUser);

        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuario", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        DetalheUsuarioAcesso usuarioData = (DetalheUsuarioAcesso) authResult.getPrincipal();

        String token = JWT.create().
                withSubject(usuarioData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
                .sign(Algorithm.HMAC512(TOKEN_SENHA));
        ResponseTokenJWTDTO tkn  = new ResponseTokenJWTDTO(token);

        response.getWriter().write( new ObjectMapper ().writeValueAsString(tkn));
        response.getWriter().flush();
    }

}

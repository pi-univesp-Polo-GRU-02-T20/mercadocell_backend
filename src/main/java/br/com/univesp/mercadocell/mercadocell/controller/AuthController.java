package br.com.univesp.mercadocell.mercadocell.controller;


import br.com.univesp.mercadocell.mercadocell.dto.jwt.MessageResponseDTO;
import br.com.univesp.mercadocell.mercadocell.model.Usuario;
import br.com.univesp.mercadocell.mercadocell.security.jwt.jwt.JwtUtils;
import br.com.univesp.mercadocell.mercadocell.dto.jwt.SignupRequestDTO;
import br.com.univesp.mercadocell.mercadocell.security.jwt.service.UserDetailsImpl;
import br.com.univesp.mercadocell.mercadocell.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import br.com.univesp.mercadocell.mercadocell.dto.jwt.LoginRequestDTO;
import br.com.univesp.mercadocell.mercadocell.dto.jwt.JwtResponseDTO;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginRequestDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getSenha()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponseDTO(
                userDetails.getId(),
                userDetails.getUsername(),
                jwt
                ));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> registerUser( @RequestBody SignupRequestDTO signUpRequest) {
        Usuario usuarioCad = new Usuario();
        usuarioCad.setLogin(signUpRequest.getLogin());
        usuarioCad.setSenha(signUpRequest.getSenha());
        usuarioService.cadastrarUsuario(usuarioCad);
        return ResponseEntity.ok(new MessageResponseDTO("Usuário cadastrado com sucesso!"));
    }













}

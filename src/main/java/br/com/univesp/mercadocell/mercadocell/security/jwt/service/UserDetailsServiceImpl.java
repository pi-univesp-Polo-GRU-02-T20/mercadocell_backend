package br.com.univesp.mercadocell.mercadocell.security.jwt.service;


import br.com.univesp.mercadocell.mercadocell.model.Usuario;
import br.com.univesp.mercadocell.mercadocell.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username){
        Usuario usuario = usuarioService.buscarUsuarioPorLogin(username);
        return UserDetailsImpl.build(usuario);
    }

}

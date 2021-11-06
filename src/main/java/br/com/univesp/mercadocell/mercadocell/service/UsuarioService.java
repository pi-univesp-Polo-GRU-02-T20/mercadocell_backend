package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Pessoa;
import br.com.univesp.mercadocell.mercadocell.model.Usuario;
import br.com.univesp.mercadocell.mercadocell.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    public void cadastrarUsuario(Usuario usuario) {
        usuarioRepository.cadastrarUsuario(usuario);
    }

    public Usuario buscarUsuarioPorId(int  idUsuario) {
        return usuarioRepository.buscarUsuarioPorId(idUsuario);
    }
    public Usuario buscarUsuarioPorLogin(String  loginUsuario) {
        return usuarioRepository.buscarUsuarioPorLogin(loginUsuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioRepository.atualizarUsuario(usuario);
    }

    public void deletarUsuario(int idUsuario) {
        usuarioRepository.deletarUsuario(idUsuario);
    }
}

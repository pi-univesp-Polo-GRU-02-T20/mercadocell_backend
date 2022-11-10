package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.dto.jwt.UsuarioSenhaTrocaDTO;
import br.com.univesp.mercadocell.mercadocell.model.Usuario;
import br.com.univesp.mercadocell.mercadocell.repository.UsuarioRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import br.com.univesp.mercadocell.mercadocell.service.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder psEncoder;
    private static final String DIGEST = "SHA3-256";

    public Usuario cadastrarUsuario(Usuario usuario) {
        try {
            usuarioRepository.buscarUsuarioPorLogin(usuario.getLogin());
            throw new EntityIntegrityViolationException("Usuário já cadastrado: " + usuario.toString());
        }catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            //usuario.setComplementoSenha(String.valueOf(System.currentTimeMillis()));
            //usuario.setSenha(psEncoder.encode(usuario.getSenha().concat(usuario.getComplementoSenha()))); // encriptação da senha
            usuario.setSenha(psEncoder.encode(usuario.getSenha())); // encriptação da senha

            try {
                usuarioRepository.cadastrarUsuario(usuario);
            } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                throw new EntityIntegrityViolationException(
                        "Dados de usuário inconsistentes:" + usuario.toString());
            }
        }
        usuario.setSenha(UsuarioRepository.MASCARA_SENHA);
        usuario.setComplementoSenha(null);
        return usuario;
    }

    public Usuario buscarUsuarioPorId(int  idUsuario) {
        try{
            return usuarioRepository.buscarUsuarioPorId(idUsuario);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Código de usuário não encontrado: " + idUsuario);
        }
    }

    public Usuario buscarUsuarioPorLogin(String  loginUsuario) {
        try{
            return usuarioRepository.buscarUsuarioPorLogin(loginUsuario);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Usuário não encontrado: " + loginUsuario);
        }
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }

    public void atualizarUsuario(Usuario usuario) {
        try{
            Usuario usuarioBD = buscarUsuarioPorLogin(usuario.getLogin());
            usuario.setSenha(psEncoder.encode(usuario.getSenha())); // encriptação da senha
            usuarioRepository.atualizarUsuario(usuario);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Usuario não encontrado: " + usuario.toString());
        }catch(DataIntegrityViolationException e ){
            throw new EntityIntegrityViolationException(
                    "Dados de usuário inconsistentes:" + usuario.toString());
        }
    }

    // não é necessário verificar existência de usuário antes de apagar por não haver tabela dependente
    public void deletarUsuario(int idUsuario) {
        usuarioRepository.deletarUsuario(idUsuario);
    }

    public Boolean validarSenha(Usuario usuario){
        Usuario usuarioBD = buscarUsuarioPorLogin(usuario.getLogin());
        //return psEncoder.matches( usuario.getSenha().concat(usuarioBD.getComplementoSenha()), usuarioBD.getSenha());
        return psEncoder.matches( usuario.getSenha(), usuarioBD.getSenha());
    }

    public void atualizarSenha(UsuarioSenhaTrocaDTO usuarioSenhaTrocaDTO) {
        try{
            Usuario usuario = buscarUsuarioPorLogin(usuarioSenhaTrocaDTO.getLogin());
            if (psEncoder.matches(usuarioSenhaTrocaDTO.getSenha(), usuario.getSenha())){
                usuario.setSenha(psEncoder.encode(usuarioSenhaTrocaDTO.getSenhaNova())); // encriptação da senha
                try{
                    usuarioRepository.atualizarUsuario(usuario);
                }catch(DataIntegrityViolationException e ){
                    throw new EntityIntegrityViolationException(
                            "Dados de usuário inconsistentes:" + usuario.toString());
                }
            }else {
                throw new SenhaInvalidaException("Senha inválida");
            }
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Usuario não encontrado: " + usuarioSenhaTrocaDTO.toString());
        }
    }
}

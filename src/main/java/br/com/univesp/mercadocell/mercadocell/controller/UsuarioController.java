package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.Usuario;
import br.com.univesp.mercadocell.mercadocell.repository.UsuarioRepository;
import br.com.univesp.mercadocell.mercadocell.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder psEncoder;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
        usuario.setSenha(psEncoder.encode(usuario.getSenha())); // encriptação da senha
        usuarioService.cadastrarUsuario(usuario);
        usuario.setSenha(UsuarioRepository.mascaraSenha);
        return ResponseEntity.accepted().body(usuario);
    }

    @GetMapping(path="/{idUsuario}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable int idUsuario) {
        Optional<Usuario> usuarioOpt =
                Optional.ofNullable(usuarioService.buscarUsuarioPorId(idUsuario));
        if (usuarioOpt.isPresent()){
            return new ResponseEntity<Usuario>(usuarioOpt.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<Usuario>(
                    new Usuario(0, "Não Encontrado",null, null, null
                    ), HttpStatus.OK
            );
        }
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PutMapping
    public ResponseEntity<Usuario> atualizarUsuario(@Valid @RequestBody Usuario usuario) {
        usuario.setSenha(psEncoder.encode(usuario.getSenha())); // encriptação da senha
        usuarioService.atualizarUsuario(usuario);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Usuario> deletarUsuario(@PathVariable int idUsuario) {
        usuarioService.deletarUsuario(idUsuario);
        return ResponseEntity.noContent().build();
    }

    // todo terminar validação de senha
    @PostMapping("validarSenha")
    public ResponseEntity<Boolean> validarSenha(@Valid @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOpt = Optional.ofNullable(usuarioService.buscarUsuarioPorLogin(usuario.getLogin()));
        if(usuarioOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
        Usuario usuarioBd = usuarioOpt.get();
        boolean valido = psEncoder.matches( usuario.getSenha(), usuarioBd.getSenha());
        HttpStatus statusSenha = (valido)? HttpStatus.OK: HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(statusSenha).body(valido);
    }
}
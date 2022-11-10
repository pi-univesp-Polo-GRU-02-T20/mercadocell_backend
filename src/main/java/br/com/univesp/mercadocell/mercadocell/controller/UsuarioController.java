package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.Usuario;
import br.com.univesp.mercadocell.mercadocell.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
        usuario = usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.accepted().body(usuario);
    }

    @GetMapping(path="/{idUsuario}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable int idUsuario) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PutMapping
    public ResponseEntity<Usuario> atualizarUsuario(@Valid @RequestBody Usuario usuario) {
        usuarioService.atualizarUsuario(usuario);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Usuario> deletarUsuario(@PathVariable int idUsuario) {
        usuarioService.deletarUsuario(idUsuario);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("validarSenha")
    public ResponseEntity<Boolean> validarSenha(@Valid @RequestBody Usuario usuario) {
        boolean valido = usuarioService.validarSenha(usuario);
        HttpStatus statusSenha = valido ? HttpStatus.OK: HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(statusSenha).body(valido);
    }
}

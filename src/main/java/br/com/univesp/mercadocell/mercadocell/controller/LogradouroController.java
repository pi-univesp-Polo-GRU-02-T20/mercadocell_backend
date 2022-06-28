package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.Logradouro;
import br.com.univesp.mercadocell.mercadocell.service.LogradouroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@RestController // Anotação de Serviços Rest
//@CrossOrigin
//@RequestMapping("/logradouro") //Define a url que quando for requisitada chamará o método a seguir
public class LogradouroController {

    @Autowired // Anotação para a Injeção automática de Dependências
    private LogradouroService logradouroService;

    @PostMapping // Mapeamento HTTP
    public ResponseEntity<Logradouro> cadastrarLogradouro(@Valid @RequestBody Logradouro logradouro) {
        logradouroService.cadastrarLogradouro(logradouro);
        return ResponseEntity.accepted().body(logradouro);
    }   

    @GetMapping(path="/{idLogradouro}")
    public ResponseEntity<Logradouro> buscarLogradouroPorId(@PathVariable int idLogradouro) {
        Logradouro logradouro = logradouroService.buscarLogradouroPorId(idLogradouro);
          return ResponseEntity.ok().body(logradouro);
    }

    @GetMapping
    public List<Logradouro> listarLogradouros() {
        return logradouroService.listarLogradouros();
    }

    @PutMapping
    public ResponseEntity<Logradouro> atualizarLogradouro(@Valid @RequestBody Logradouro logradouro) {
        logradouroService.atualizarLogradouro(logradouro);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idLogradouro}")
    public ResponseEntity<Logradouro> deletarLogradouro(@PathVariable int idLogradouro) {
        logradouroService.deletarLogradouro(idLogradouro);
        return ResponseEntity.noContent().build();
    }
}
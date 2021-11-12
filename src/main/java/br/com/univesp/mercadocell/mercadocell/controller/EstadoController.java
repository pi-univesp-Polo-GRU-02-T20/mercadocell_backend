package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.Estado;
import br.com.univesp.mercadocell.mercadocell.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

//@RestController // Anotação de Serviços Rest
//@CrossOrigin
//@RequestMapping("/estado") //Define a url que quando for requisitada chamará o método a seguir
public class EstadoController {

    @Autowired // Anotação para a Injeção automática de Dependências
    private EstadoService estadoService;

    @PostMapping // Mapeamento HTTP
    public ResponseEntity<Estado> cadastrarEstado(@Valid @RequestBody Estado estado) {
        estadoService.cadastrarEstado(estado);
        return ResponseEntity.accepted().body(estado);
    }   

    @GetMapping(path="/{idEstado}")
    public ResponseEntity<Estado> buscarEstadoPorId(@PathVariable int idEstado) {
        Optional<Estado> estadoOpt = Optional.ofNullable(estadoService.buscarEstadoPorId(idEstado));
        if (estadoOpt.isPresent()){
            return new ResponseEntity<Estado>(estadoOpt.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<Estado>(new Estado(0,"Não Encontrado"), HttpStatus.OK);
        }
    }

    @GetMapping
    public List<Estado> listarEstados() {
        return estadoService.listarEstados();
    }

    @PutMapping
    public ResponseEntity<Estado> atualizarEstado(@Valid @RequestBody Estado estado) {
        estadoService.atualizarEstado(estado);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idEstado}")
    public ResponseEntity<Estado> deletarEstado(@PathVariable int idEstado) {
        estadoService.deletarEstado(idEstado);
        return ResponseEntity.noContent().build();
    }
}
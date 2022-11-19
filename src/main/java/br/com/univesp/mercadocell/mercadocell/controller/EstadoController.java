package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.Estado;
import br.com.univesp.mercadocell.mercadocell.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // Anotação de Serviços Rest
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/estado") //Define a url que quando for requisitada chamará o método a seguir
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
        Estado estado = estadoService.buscarEstadoPorId(idEstado);
        return  ResponseEntity.ok().body(estado);
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
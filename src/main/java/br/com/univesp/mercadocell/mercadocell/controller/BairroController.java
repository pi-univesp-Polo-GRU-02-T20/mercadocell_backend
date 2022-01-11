package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.Bairro;
import br.com.univesp.mercadocell.mercadocell.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("bairro")
public class BairroController {

    @Autowired
    private BairroService bairroService;

    @PostMapping
    public ResponseEntity<Bairro> cadastrarBairro(@Valid @RequestBody Bairro bairro) {
        bairroService.cadastrarBairro(bairro);
        return ResponseEntity.accepted().body(bairro);
    }

    @GetMapping(path="/{idBairro}")
    public ResponseEntity<Bairro> buscarBairroPorId(@PathVariable int idBairro) {
        Bairro bairro = bairroService.buscarBairroPorId(idBairro);
        return ResponseEntity.ok().body(bairro);
    }

    @GetMapping
    public List<Bairro> listarBairros() {
        return bairroService.listarBairros();
    }

    @PutMapping
    public ResponseEntity<Bairro> atualizarBairro(@Valid @RequestBody Bairro bairro) {
        bairroService.atualizarBairro(bairro);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idBairro}")
    public ResponseEntity<Bairro> deletarBairro(@PathVariable int idBairro) {
        bairroService.deletarBairro(idBairro);
        return ResponseEntity.noContent().build();
    }

}

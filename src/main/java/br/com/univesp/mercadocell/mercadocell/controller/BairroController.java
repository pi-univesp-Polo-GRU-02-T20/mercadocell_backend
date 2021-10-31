package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.Bairro;
import br.com.univesp.mercadocell.mercadocell.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
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
        Optional<Bairro> bairroOpt = Optional.ofNullable(BairroService.buscarBairroPorId(idBairro));
        if (bairroOpt.isPresent()){
            return new ResponseEntity<Bairro>(bairroOpt.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<Bairro>(new Bairro(0, "Não Encontrado"), HttpStatus.OK);
        }
    }

    @GetMapping
    public List<Bairro> listarBairros() {
        return BairroService.listarBairros();
    }

    @PutMapping
    public ResponseEntity<?> atualizarBairro(@Valid @RequestBody Bairro bairroOpt) {
        BairroService.atualizarBairro(bairroOpt);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idBairro}")
    public ResponseEntity<?> deletarBairro(@PathVariable int idBairro) {
        BairroService.deletarBairro(idBairro);
        return ResponseEntity.noContent().build();
    }

}

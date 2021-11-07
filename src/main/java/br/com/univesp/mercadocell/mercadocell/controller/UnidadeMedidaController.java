package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.UnidadeMedida;
import br.com.univesp.mercadocell.mercadocell.service.UnidadeMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("unidadeMedida")
public class UnidadeMedidaController {

    @Autowired
    private UnidadeMedidaService unidadeMedidaService;

    @PostMapping
    public ResponseEntity<UnidadeMedida> cadastrarUnidadeMedida(@Valid @RequestBody UnidadeMedida unidadeMedida) {
        unidadeMedidaService.cadastrarUnidadeMedida(unidadeMedida);
        return ResponseEntity.accepted().body(unidadeMedida);
    }

    @GetMapping(path="/{idUnidadeMedida}")
    public ResponseEntity<UnidadeMedida> buscarUnidadeMedidaPorId(@PathVariable int idUnidadeMedida) {
        Optional<UnidadeMedida> unidadeMedidaOpt =
                Optional.ofNullable(unidadeMedidaService.buscarUnidadeMedidaPorId(idUnidadeMedida));
        if (unidadeMedidaOpt.isPresent()){
            return new ResponseEntity<UnidadeMedida>(unidadeMedidaOpt.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<UnidadeMedida>(
                        new UnidadeMedida(0, "Não Encontrado",""
                    ), HttpStatus.OK);
        }
    }

    @GetMapping
    public List<UnidadeMedida> listarUnidadesMedida(){
        return unidadeMedidaService.listarUnidadesMedida();
    }

    @PutMapping
    public ResponseEntity<UnidadeMedida> atualizarUnidadeMedida(@Valid @RequestBody UnidadeMedida unidadeMedida) {
        unidadeMedidaService.atualizarUnidadeMedida(unidadeMedida);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idUnidadeMedida}")
    public ResponseEntity<UnidadeMedida> deletarUnidadeMedida(@PathVariable int idUnidadeMedida) {
        unidadeMedidaService.deletarUnidadeMedida(idUnidadeMedida);
        return ResponseEntity.noContent().build();
    }
}

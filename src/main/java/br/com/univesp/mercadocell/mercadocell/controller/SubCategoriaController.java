package br.com.univesp.mercadocell.mercadocell.controller;


import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import br.com.univesp.mercadocell.mercadocell.model.SubCategoria;
import br.com.univesp.mercadocell.mercadocell.service.SubCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("subcategoria")
public class SubCategoriaController {

    @Autowired
    private SubCategoriaService subCategoriaService;

    @PostMapping
    public ResponseEntity<?> cadastrarSubCategoria(@Valid @RequestBody SubCategoria subcategoria) {
        subCategoriaService.cadastrarSubCategoria(subcategoria);
        return ResponseEntity.accepted().body(subcategoria);
    }

    @GetMapping(path="/{idSubCategoria}")
    public ResponseEntity<SubCategoria> buscarSubCategoriaPorId(@PathVariable int idSubCategoria) {
        Optional<SubCategoria> subCategoriaOpt = Optional.ofNullable(subCategoriaService.buscarSubCategoriaPorId(idSubCategoria));
        if (subCategoriaOpt.isPresent()){
            return new ResponseEntity<SubCategoria>(subCategoriaOpt.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<SubCategoria>(new SubCategoria(0,"Não Encontrado",
                                                                        new Categoria(0, "Não Encontrado")
                    ), HttpStatus.OK);
        }
    }

    @GetMapping
    public List<SubCategoria> listarSubCategorias() {
        return subCategoriaService.listarSubCategorias();
    }

    @PutMapping
    public ResponseEntity<?> atualizarSubCategoria(@Valid @RequestBody SubCategoria subCategoria) {
        subCategoriaService.atualizarSubCategoria(subCategoria);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idSubCategoria}")
    public ResponseEntity<?> deletarSubCategoria(@PathVariable int idSubCategoria) {
        subCategoriaService.deletarSubCategoria(idSubCategoria);
        return ResponseEntity.noContent().build();
    }

}

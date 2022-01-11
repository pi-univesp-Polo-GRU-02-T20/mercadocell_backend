package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import br.com.univesp.mercadocell.mercadocell.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> cadastrarCategoria(@Valid @RequestBody Categoria categoria) {
        categoriaService.cadastrarCategoria(categoria);
        return ResponseEntity.accepted().body(categoria);
    }

    @GetMapping(path="/{idCategoria}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable int idCategoria) {
        Categoria categoria = categoriaService.buscarCategoriaPorId(idCategoria);
            return ResponseEntity.ok().body(categoria);
    }

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @PutMapping
    public ResponseEntity<Categoria> atualizarCategoria(@Valid @RequestBody Categoria categoria) {
        categoriaService.atualizarCategoria(categoria);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Categoria> deletarCategoria(@PathVariable int idCategoria) {
        categoriaService.deletarCategoria(idCategoria);
        return ResponseEntity.noContent().build();
    }

}

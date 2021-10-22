package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import br.com.univesp.mercadocell.mercadocell.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<?> cadastrarCategoria(@Valid @RequestBody Categoria categoria) {
        categoriaService.cadastrarCategoria(categoria);
        return ResponseEntity.accepted().body(categoria);
    }

    @GetMapping("/{idCategoria}")
    public Categoria buscarCategoriaPorId(@PathVariable int idCategoria) {
        return categoriaService.buscarCategoriaPorId(idCategoria);
    }

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @PutMapping
    public ResponseEntity<?> atualizarCategoria(@Valid @RequestBody Categoria categoria) {
        categoriaService.atualizarCategoria(categoria);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<?> deletarCategoria(@PathVariable int idCategoria) {
        categoriaService.deletarCategoria(idCategoria);
        return ResponseEntity.noContent().build();
    }

}

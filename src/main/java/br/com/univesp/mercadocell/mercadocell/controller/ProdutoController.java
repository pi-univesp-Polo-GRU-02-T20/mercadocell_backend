package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.Produto;
import br.com.univesp.mercadocell.mercadocell.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService ;

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@Valid @RequestBody Produto produto,
                                                        @RequestParam("file") MultipartFile file) {
        produtoService.cadastrarProduto(produto, file);
        return ResponseEntity.accepted().body(produto);
    }

    @GetMapping(path="/{idProduto}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable int idProduto) {
        Produto produto = produtoService.buscarProdutoPorId(idProduto);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @PutMapping
    public ResponseEntity<Produto> atualizarProduto(@Valid @RequestBody Produto produto) {
        produtoService.atualizarProduto(produto);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Produto> deletarProduto(@PathVariable int idProduto) {
        produtoService.deletarProduto(idProduto);
        return ResponseEntity.noContent().build();
    }
}
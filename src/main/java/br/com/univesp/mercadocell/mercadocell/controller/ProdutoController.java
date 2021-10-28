package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.Produto;

import br.com.univesp.mercadocell.mercadocell.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService ;

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@Valid @RequestBody Produto produto) {
        produtoService.cadastrarProduto(produto);
        return ResponseEntity.accepted().body(produto);
    }

    @GetMapping(path="/{idProduto}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable int idProduto) {
        Optional<Produto> produtoOpt =
                Optional.ofNullable(produtoService.buscarProdutoPorId(idProduto));
        if (produtoOpt.isPresent()){
            return new ResponseEntity<Produto>(produtoOpt.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<Produto>(
                    new Produto(0, "Não Encontrado",null, null, null
                            ), HttpStatus.OK
            );
        }
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
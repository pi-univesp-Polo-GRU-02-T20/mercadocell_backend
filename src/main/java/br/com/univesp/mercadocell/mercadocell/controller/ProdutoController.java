package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.dto.ProdutoDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ProdutoImagemDTO;
import br.com.univesp.mercadocell.mercadocell.model.Produto;
import br.com.univesp.mercadocell.mercadocell.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService ;

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        produtoService.cadastrarProduto(produtoDTO);
        return ResponseEntity.accepted().body(produtoDTO);
    }

    @GetMapping(path="/{idProduto}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable int idProduto) {
        return ResponseEntity.ok().body( produtoService.buscarProdutoPorId(idProduto));
    }

    @GetMapping
    public List<ProdutoDTO> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @PutMapping
    public ResponseEntity<ProdutoDTO> atualizarProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        produtoService.atualizarProduto(produtoDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> deletarProduto(@PathVariable int idProduto) {
        produtoService.deletarProduto(idProduto);
        return ResponseEntity.noContent().build();
    }
}
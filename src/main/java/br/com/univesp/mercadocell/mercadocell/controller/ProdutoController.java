package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.dto.ProdutoConsultaDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ProdutoInputDTO;
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
    public ResponseEntity<ProdutoInputDTO> cadastrarProduto(@Valid @RequestBody ProdutoInputDTO produtoInputDTO) {
        produtoService.cadastrarProduto(produtoInputDTO);
        return ResponseEntity.accepted().body(produtoInputDTO);
    }

    @GetMapping(path="/{idProduto}")
    public ResponseEntity<ProdutoConsultaDTO> buscarProdutoPorId(@PathVariable int idProduto) {
        return ResponseEntity.ok().body( produtoService.buscarProdutoPorId(idProduto));
    }

    @GetMapping
    public List<ProdutoConsultaDTO> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @PutMapping
    public ResponseEntity<ProdutoInputDTO> atualizarProduto(@Valid @RequestBody ProdutoInputDTO produtoInputDTO) {
        produtoService.atualizarProduto(produtoInputDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<ProdutoInputDTO> deletarProduto(@PathVariable int idProduto) {
        produtoService.deletarProduto(idProduto);
        return ResponseEntity.noContent().build();
    }
}
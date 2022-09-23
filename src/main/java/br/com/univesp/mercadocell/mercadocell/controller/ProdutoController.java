package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.dto.ProdutoDTO;
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
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        produtoService.cadastrarProduto(produtoDTO);
        return ResponseEntity.accepted().body(produtoDTO);
    }

    @PostMapping("/comImagem")
    public ResponseEntity<ProdutoDTO> cadastrarProdutoComImgem(  @RequestParam("nomeProduto")	String 			nomeProduto				,
                                                               @RequestParam("descricaoProduto")	String 			descricaoProduto		,
                                                               @RequestParam("codigoSubcategoria")	Integer 		codigoSubcategoria		,
                                                               @RequestParam("codigoUnidadeMedida")	Integer 		codigoUnidadeMedida		,
                                                               @RequestParam("quantidadeEstoqueMinimo")	Integer 		quantidadeEstoqueMinimo	,
                                                               @RequestParam("quantidadeEstoqueMaximo")	Integer 		quantidadeEstoqueMaximo	,
                                                               @RequestParam("quantidadeEstoqueAtual")	Integer 		quantidadeEstoqueAtual	,
                                                               @RequestParam("arqImagem") 	MultipartFile 	arqImagem
                                                              ) {
        ProdutoDTO produtoDTO = new ProdutoDTO(
                null			,
                nomeProduto				,
                descricaoProduto		,
                codigoSubcategoria		,
                codigoUnidadeMedida		,
                quantidadeEstoqueMinimo	,
                quantidadeEstoqueMaximo	,
                quantidadeEstoqueAtual

        );
        produtoService.cadastrarProdutoComImagem(produtoDTO, arqImagem);
        return ResponseEntity.accepted().body(produtoDTO);
    }

    @GetMapping(path="/{produtoId}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable int produtoId) {
        return ResponseEntity.ok().body( produtoService.buscarProdutoPorId(produtoId));
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

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<ProdutoDTO> deletarProduto(@PathVariable int produtoId) {
        produtoService.deletarProduto(produtoId);
        return ResponseEntity.noContent().build();
    }
}
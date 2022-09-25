package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.dto.ProdutoComImagemDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ProdutoDTO;
import br.com.univesp.mercadocell.mercadocell.model.Imagem;
import br.com.univesp.mercadocell.mercadocell.service.ImagemService;
import br.com.univesp.mercadocell.mercadocell.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService ;

    @Autowired
    private ImagemService imagemService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        produtoService.cadastrarProduto(produtoDTO);
        return ResponseEntity.accepted().body(produtoDTO);
    }

    @PostMapping("/cadastrarProdutoComImagem")
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
    public List<ProdutoComImagemDTO> listarProdutos() {
        List<ProdutoComImagemDTO> listaProdutosComImagemDTO = new ArrayList<>();
        for (ProdutoDTO produtoDTO : produtoService.listarProdutos()){
            List<String> urlProdutos = new ArrayList<String>();
            for(Imagem imagem: imagemService.buscarImagemProdutoPorId(produtoDTO.getCodProduto()) ){
                String fileDownloadUri = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path(ImagemController.URL_IMAGENS_PRODUTO)
                        .path(imagem.getCodigoImagem().toString())
                        .toUriString();
                urlProdutos.add(fileDownloadUri);
            }
            listaProdutosComImagemDTO.add(
                    new ProdutoComImagemDTO(
                            produtoDTO.getCodProduto(),
                            produtoDTO.getNomeProduto(),
                            produtoDTO.getDescricaoProduto(),
                            produtoDTO.getCodigoSubcategoria(),
                            produtoDTO.getCodigoUnidadeMedida(),
                            produtoDTO.getQuantidadeEstoqueMinimo(),
                            produtoDTO.getQuantidadeEstoqueMaximo(),
                            produtoDTO.getQuantidadeEstoqueAtual(),
                            urlProdutos
                    )
            );
        }
        return listaProdutosComImagemDTO;
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
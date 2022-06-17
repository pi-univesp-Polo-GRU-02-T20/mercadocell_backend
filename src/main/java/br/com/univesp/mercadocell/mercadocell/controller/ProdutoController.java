package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.dto.ProdutoDTO;
import br.com.univesp.mercadocell.mercadocell.message.ResponseFile;
import br.com.univesp.mercadocell.mercadocell.model.Produto;
import br.com.univesp.mercadocell.mercadocell.service.ImagemService;
import br.com.univesp.mercadocell.mercadocell.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService ;
    @Autowired
    private ImagemService imagemService ;
    private static final String URL_IMAGENS_PRODUTO = "imagem/";

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@Valid @RequestBody Produto produto) {
        produtoService.cadastrarProduto(produto);
        return ResponseEntity.accepted().body(produto);
    }

    @GetMapping(path="/{idProduto}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable int idProduto) {
        return ResponseEntity.ok().body( produtoService.buscarProdutoPorId(idProduto));
    }

    @GetMapping(path="/imagem/{idProduto}")
    public ResponseEntity<List<ResponseFile>> buscarImagemProdutoPorId(@PathVariable Integer idProduto) {
        List<ResponseFile> arquivosImagem = imagemService.buscarImagemProdutoPorId(idProduto.intValue()).stream()
                .map(imagem -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path(URL_IMAGENS_PRODUTO)
                            .path(imagem.getCodigoImagem().toString())
                            .toUriString();

                    return new ResponseFile(
                            imagem.getNomeImagem(),
                            fileDownloadUri,
                            imagem.getTipoImagem(),
                            imagem.getBinarioImagem().length);
                }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(arquivosImagem);

    }


    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @PutMapping
    public ResponseEntity<ProdutoDTO> atualizarProduto(@Valid @RequestBody Produto produto) {
        produtoService.atualizarProduto(produto);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> deletarProduto(@PathVariable int idProduto) {
        produtoService.deletarProduto(idProduto);
        return ResponseEntity.noContent().build();
    }
}
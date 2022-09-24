package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.dto.CatalogoProdutoDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ProdutoDTO;
import br.com.univesp.mercadocell.mercadocell.model.CatalogoProduto;
import br.com.univesp.mercadocell.mercadocell.model.Imagem;
import br.com.univesp.mercadocell.mercadocell.model.ItemOperacao;
import br.com.univesp.mercadocell.mercadocell.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("catalogo-produto")
public class CatalogoProdutoController {

    @Autowired
    private ProdutoService produtoService ;

    @GetMapping()
    public ResponseEntity<List<CatalogoProdutoDTO>> listarCatalogoProdutos() {
        List<CatalogoProdutoDTO> listaCatalogoProdutosDto = new ArrayList<CatalogoProdutoDTO>();
        for (CatalogoProduto produto : produtoService.listarCatalogoProdutos()){
            List<String> urlProdutos = new ArrayList<String>();
            for(Imagem imagem: produto.getListaImagens()){
                String fileDownloadUri = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path(ImagemController.URL_IMAGENS_PRODUTO)
                        .path(imagem.getCodigoImagem().toString())
                        .toUriString();
                urlProdutos.add(fileDownloadUri);
            }
            listaCatalogoProdutosDto.add(
                    new CatalogoProdutoDTO(
                                    produto.getNomeProduto(),
                                    produto.getDescricaoProduto(),
                                    produto.getNomeSubCategoria(),
                                    produto.getNomeCategoria(),
                                    produto.getNomeUnidadeMedida(),
                                    urlProdutos
                        )
                    );
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaCatalogoProdutosDto);
    }

}
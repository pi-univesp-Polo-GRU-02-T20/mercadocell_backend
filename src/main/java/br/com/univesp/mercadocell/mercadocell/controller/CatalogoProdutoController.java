package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.dto.CatalogoProdutoDTO;
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
@RequestMapping("catalogo-produto")
public class CatalogoProdutoController {

    @Autowired
    private ProdutoService produtoService ;

    @GetMapping
    public List<CatalogoProdutoDTO> listarCatalogoProdutos() {
        return produtoService.listarCatalogoProdutos();
    }

}
package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.ItemOperacao;
import br.com.univesp.mercadocell.mercadocell.service.ItemOperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("itemOperacao")
public class ItemOperacaoController {

    @Autowired
    ItemOperacaoService itemOperacaoService;

    @GetMapping(path="/relatorios/categoria")
    public List<ItemOperacao> listarItensOperacaoPorCategoria(@RequestParam String nomeCategoria,
                                                              @RequestParam String tipoOperacao) {
        return itemOperacaoService.listarItensOperacaoPorCategoria(nomeCategoria, tipoOperacao);
    }
    @GetMapping(path="/relatorios/produto")
    public List<ItemOperacao> listarItensOperacaoPorProduto(@RequestParam String nomeProduto,
                                                            @RequestParam String tipoOperacao) {
        return  itemOperacaoService.listarItensOperacaoPorProduto(nomeProduto, tipoOperacao);
    }

    @GetMapping(path="/relatorios/tipoOperacao")
    public List<ItemOperacao> listarItensOperacaoPorTipoOperacao(@RequestParam String tipoOperacao) {
        return itemOperacaoService.listarItensOperacaoPorTipoOperacao(tipoOperacao);
    }

    @PostMapping
    public void incluirItensOperacao(@Valid @RequestBody List<ItemOperacao> listaItensOperacao) {
        itemOperacaoService.incluirItensOperacao(listaItensOperacao);
    }

    @DeleteMapping
    public void removerItensOperacao(@Valid @RequestBody List<ItemOperacao> listaItensOperacao) {
        itemOperacaoService.removerItensOperacao(listaItensOperacao);
    }

}

package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.ItemOperacao;
import br.com.univesp.mercadocell.mercadocell.service.ItemOperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("itemOperacao")
public class ItemOperacaoController {

    @Autowired
    ItemOperacaoService itemOperacaoService;

    @GetMapping(path="/relatorios/operacao/categoria")
    public List<ItemOperacao> listarItensOperacaoPorCategoria(@RequestParam String nomeCategoria,
                                                          @RequestParam String tipoOperacao) {
        List<ItemOperacao> listaItensCategoria =itemOperacaoService.listarItensOperacaoPorCategoria(nomeCategoria, tipoOperacao);
        return listaItensCategoria;
    }
    @GetMapping(path="/relatorios/operacao/produto")
    public List<ItemOperacao> listarItensOperacaoPorProduto(@RequestParam String nomeProduto,
                                                          @RequestParam String tipoOperacao) {
        List<ItemOperacao> listaItensCategoria =itemOperacaoService.listarItensOperacaoPorProduto(nomeProduto, tipoOperacao);
        return listaItensCategoria;
    }

    @GetMapping(path="/operacao/incluirItem")
    public void incluirItensOperacao(@RequestParam List<ItemOperacao> listaItensOperacao) {
        itemOperacaoService.incluirItensOperacao(listaItensOperacao);
    }

}

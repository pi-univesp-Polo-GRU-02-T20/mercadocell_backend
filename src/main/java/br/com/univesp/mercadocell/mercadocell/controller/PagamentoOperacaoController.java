package br.com.univesp.mercadocell.mercadocell.controller;


import br.com.univesp.mercadocell.mercadocell.model.PagamentoOperacao;
import br.com.univesp.mercadocell.mercadocell.service.PagamentoOperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pagamentoOperacao")
public class PagamentoOperacaoController {

    @Autowired
    PagamentoOperacaoService pagamentoOperacaoService;

    @PostMapping
    public void incluirPagamentoOperacao(@Valid @RequestBody PagamentoOperacao pagamentoOperacao) {
        pagamentoOperacaoService.incluirPagamentoOperacao(pagamentoOperacao);
    }

    @DeleteMapping
    public void removerOperacao(@RequestParam Integer idPagamento) {
        pagamentoOperacaoService.removerPagamentoOperacao(idPagamento);
    }

    @GetMapping(path="idPagamento")
    public List<PagamentoOperacao> buscarPagamentoPorOperacaoId(@RequestParam Integer idPagamento) {
        return pagamentoOperacaoService.buscarPagamentoPorOperacaoId(idPagamento);
    }

    @GetMapping
    public List<PagamentoOperacao> listarPagamentoPorTipoOperacao(@RequestParam String tipoOperacao) {
        return pagamentoOperacaoService.listarPagamentoPorTipoOperacao(tipoOperacao);
    }
}

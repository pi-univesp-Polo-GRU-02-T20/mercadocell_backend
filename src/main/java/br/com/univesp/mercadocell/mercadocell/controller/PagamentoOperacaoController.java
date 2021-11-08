package br.com.univesp.mercadocell.mercadocell.controller;


import br.com.univesp.mercadocell.mercadocell.model.PagamentoOperacao;
import br.com.univesp.mercadocell.mercadocell.model.dto.ItemOperacaoDTO;
import br.com.univesp.mercadocell.mercadocell.service.PagamentoOperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pagamentoOperacao")
public class PagamentoOperacaoController {
    //todo implementar CRUD pagamento Operacao
    @Autowired
    PagamentoOperacaoService pagamentoOperacaoService;

    @GetMapping
    public List<PagamentoOperacao> listarPagamentosOperacao(@RequestParam String tipoOperacao) {
        return null;
    }

    @GetMapping(path="idOperacao")
    public List<PagamentoOperacao> buscarPagamentosOperacao(@RequestParam Integer idOperacao) {
        return null;
    }

    @GetMapping(path="/tipoOperacao")
    public List<ItemOperacaoDTO> listarItensOperacaoPorTipoOperacao(@RequestParam String tipoOperacao) {
        return null;
    }

    @PostMapping
    public void incluirPagamentoOperacao(@Valid @RequestBody PagamentoOperacao pagamentoOperacao) {

    }

    @DeleteMapping
    public void removerRemoverOperacao(@Valid @RequestBody  PagamentoOperacao pagamentoOperacao) {

    }


}

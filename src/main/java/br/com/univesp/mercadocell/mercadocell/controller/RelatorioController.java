package br.com.univesp.mercadocell.mercadocell.controller;


import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoDTO;
import br.com.univesp.mercadocell.mercadocell.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = {"faturamentoProduto"})
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public List<ProdutoRelatorioFaturamentoDTO> listarRelatorios() {
        return relatorioService.carregarRelatorioFaturamento();
    }

}

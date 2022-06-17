package br.com.univesp.mercadocell.mercadocell.controller;


import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoSMO;
import br.com.univesp.mercadocell.mercadocell.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = {"faturamento"})
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping(path="/detalhadoDiario/")
    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoDiario() {
        return relatorioService.carregarRelatorioFaturamentoDetalhadoDiario();
    }

    @GetMapping(path="/detalhadoMensal/{mesAnoFaturamento}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoDTO>> carregarRelatorioFaturamentoDetalhadoMensal
            (@PathVariable String mesAnoFaturamento) {
        List<ProdutoRelatorioFaturamentoDTO> produtoRelatorioFaturamentoDTO =
                relatorioService.carregarRelatorioFaturamentoDetalhadoMensal(mesAnoFaturamento);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoDTO);
    }

    @GetMapping(path="/detalhadoAnual/{anoFaturamento}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoDTO>> carregarRelatorioFaturamentoDetalhadoAnual
            (@PathVariable String anoFaturamento) {
        List<ProdutoRelatorioFaturamentoDTO> produtoRelatorioFaturamentoDTO =
                relatorioService.carregarRelatorioFaturamentoDetalhadoAnual(anoFaturamento);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoDTO);
    }

    @GetMapping(path="/sumarizadoDiario/")
    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioSumarizadoDetalhadoDiario() {
        return relatorioService.carregarRelatorioFaturamentoSumarizadoDiario();
    }

    @GetMapping(path="/sumarizadoMensal/{mesAnoFaturamento}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoSMO>> carregarRelatorioSumarizadoDetalhadoMensal
            (@PathVariable String mesAnoFaturamento) {
        List<ProdutoRelatorioFaturamentoSMO> produtoRelatorioFaturamentoSMO =
                relatorioService.carregarRelatorioFaturamentoSumarizadoMensal(mesAnoFaturamento);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoSMO);
    }

    @GetMapping(path="/sumarizadoAnual/{anoFaturamento}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoSMO>> carregarRelatorioSumarizadoDetalhadoAnual
            (@PathVariable String anoFaturamento) {
        List<ProdutoRelatorioFaturamentoSMO> produtoRelatorioFaturamentoSMO =
                relatorioService.carregarRelatorioFaturamentoSumarizadoAnual(anoFaturamento);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoSMO);
    }

}

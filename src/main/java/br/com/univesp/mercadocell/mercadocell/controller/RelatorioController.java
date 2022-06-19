package br.com.univesp.mercadocell.mercadocell.controller;


import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoSMO;
import br.com.univesp.mercadocell.mercadocell.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping(path="/detalhadoDiario/periodo/{dataInicio, dataTermino}")
    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoDiarioPeriodo
            (@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     LocalDate dataInicio,
             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     LocalDate dataTermino) {
        return relatorioService.carregarRelatorioFaturamentoDetalhadoDiarioPeriodo(dataInicio, dataTermino);
    }

    @GetMapping(path="/detalhadoMensal/")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoDTO>> carregarRelatorioFaturamentoDetalhadoMensalNull() {
        List<ProdutoRelatorioFaturamentoDTO> produtoRelatorioFaturamentoDTO =
                relatorioService.carregarRelatorioFaturamentoDetalhadoMensalNull();
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoDTO);
    }

    @GetMapping(path="/detalhadoMensal/{anoMesFaturamento}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoDTO>> carregarRelatorioFaturamentoDetalhadoMensal
            (@PathVariable String anoMesFaturamento) {
        List<ProdutoRelatorioFaturamentoDTO> produtoRelatorioFaturamentoDTO =
                relatorioService.carregarRelatorioFaturamentoDetalhadoMensal(anoMesFaturamento);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoDTO);
    }

    @GetMapping(path="/detalhadoMensal/periodo/{anoMesInicio, anoMesTermino}")
    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoMensalPeriodo
            (String anoMesInicio, String anoMesTermino) {
        return relatorioService.carregarRelatorioFaturamentoDetalhadoMensalPeriodo(anoMesInicio, anoMesTermino);
    }

    @GetMapping(path="/detalhadoAnual/")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoDTO>> carregarRelatorioFaturamentoDetalhadoAnualNull() {
        List<ProdutoRelatorioFaturamentoDTO> produtoRelatorioFaturamentoDTO =
                relatorioService.carregarRelatorioFaturamentoDetalhadoAnualNull();
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoDTO);
    }

    @GetMapping(path="/detalhadoAnual/{anoFaturamento}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoDTO>> carregarRelatorioFaturamentoDetalhadoAnual
            (@PathVariable String anoFaturamento) {
        List<ProdutoRelatorioFaturamentoDTO> produtoRelatorioFaturamentoDTO =
                relatorioService.carregarRelatorioFaturamentoDetalhadoAnual(anoFaturamento);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoDTO);
    }

    @GetMapping(path="/detalhadoAnual/periodo/{anoInicial, anoTermino}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoDTO>> carregarRelatorioFaturamentoDetalhadoAnualPeriodo
            (String anoInicial, String anoTermino) {
        List<ProdutoRelatorioFaturamentoDTO> produtoRelatorioFaturamentoDTO =
                relatorioService.carregarRelatorioFaturamentoDetalhadoAnualPeriodo(anoInicial, anoTermino);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoDTO);
    }

    @GetMapping(path="/sumarizadoDiario/")
    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioSumarizadoDetalhadoDiario() {
        return relatorioService.carregarRelatorioFaturamentoSumarizadoDiario();
    }

    @GetMapping(path="/sumarizadoDiario/periodo/{dataInicio, dataTermino}")
    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoDiarioPeriodo
            (@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     LocalDate dataInicio,
             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     LocalDate dataTermino) {
        return relatorioService.carregarRelatorioFaturamentoSumarizadoDiarioPeriodo(dataInicio, dataTermino);
    }

    @GetMapping(path="/sumarizadoMensal/")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoSMO>> carregarRelatorioSumarizadoDetalhadoMensalNull() {
        List<ProdutoRelatorioFaturamentoSMO> produtoRelatorioFaturamentoSMO =
                relatorioService.carregarRelatorioFaturamentoSumarizadoMensalNull();
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoSMO);
    }

    @GetMapping(path="/sumarizadoMensal/{anoMesFaturamento}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoSMO>> carregarRelatorioSumarizadoDetalhadoMensal
            (@PathVariable String anoMesFaturamento) {
        List<ProdutoRelatorioFaturamentoSMO> produtoRelatorioFaturamentoSMO =
                relatorioService.carregarRelatorioFaturamentoSumarizadoMensal(anoMesFaturamento);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoSMO);
    }

    @GetMapping(path="/sumarizadoAnual/")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoSMO>> carregarRelatorioSumarizadoDetalhadoAnualNull() {
        List<ProdutoRelatorioFaturamentoSMO> produtoRelatorioFaturamentoSMO =
                relatorioService.carregarRelatorioFaturamentoSumarizadoAnualNull();
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoSMO);
    }

    @GetMapping(path="/sumarizadoMensal/periodo/{anoMesInicial, anoMesTermino}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoSMO>> carregarRelatorioSumarizadoDetalhadoMensalPeriodo
            (String anoMesInicial, String anoMesTermino) {
        List<ProdutoRelatorioFaturamentoSMO> produtoRelatorioFaturamentoSMO =
                relatorioService.carregarRelatorioFaturamentoSumarizadoMensalPeriodo(anoMesInicial, anoMesTermino);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoSMO);
    }

    @GetMapping(path="/sumarizadoAnual/{anoFaturamento}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoSMO>> carregarRelatorioSumarizadoDetalhadoAnual
            (@PathVariable String anoFaturamento) {
        List<ProdutoRelatorioFaturamentoSMO> produtoRelatorioFaturamentoSMO =
                relatorioService.carregarRelatorioFaturamentoSumarizadoAnual(anoFaturamento);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoSMO);
    }

    @GetMapping(path="/sumarizadoAnual/periodo/{anoInicial, anoTermino}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoSMO>> carregarRelatorioSumarizadoDetalhadoAnualPeriodo
            (String anoInicial, String anoTermino) {
        List<ProdutoRelatorioFaturamentoSMO> produtoRelatorioFaturamentoSMO =
                relatorioService.carregarRelatorioFaturamentoSumarizadoAnualPeriodo(anoInicial, anoTermino);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoSMO);
    }
}

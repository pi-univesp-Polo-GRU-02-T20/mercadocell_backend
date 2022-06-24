package br.com.univesp.mercadocell.mercadocell.controller;


import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoSumarizadoDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoDetalhadoDTO;
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
    public List<ProdutoRelatorioFaturamentoSumarizadoDTO> carregarRelatorioFaturamentoDetalhadoDiario() {
        return relatorioService.carregarRelatorioFaturamentoDetalhadoDiario();
    }

    @GetMapping(path="/detalhadoDiario/periodo/{dataInicio, dataTermino}")
    public List<ProdutoRelatorioFaturamentoSumarizadoDTO> carregarRelatorioFaturamentoDetalhadoDiarioPeriodo
            (@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     LocalDate dataInicio,
             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     LocalDate dataTermino) {
        return relatorioService.carregarRelatorioFaturamentoDetalhadoDiarioPeriodo(dataInicio, dataTermino);
    }

    @GetMapping(path="/detalhadoMensal/")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoSumarizadoDTO>> carregarRelatorioFaturamentoDetalhadoMensalNull() {
        List<ProdutoRelatorioFaturamentoSumarizadoDTO> produtoRelatorioFaturamentoSumarizadoDTO =
                relatorioService.carregarRelatorioFaturamentoDetalhadoMensalNull();
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoSumarizadoDTO);
    }

    @GetMapping(path="/detalhadoMensal/{anoMesFaturamento}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoSumarizadoDTO>> carregarRelatorioFaturamentoDetalhadoMensal
            (@PathVariable String anoMesFaturamento) {
        List<ProdutoRelatorioFaturamentoSumarizadoDTO> produtoRelatorioFaturamentoSumarizadoDTO =
                relatorioService.carregarRelatorioFaturamentoDetalhadoMensal(anoMesFaturamento);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoSumarizadoDTO);
    }

    @GetMapping(path="/detalhadoMensal/periodo/{anoMesInicio, anoMesTermino}")
    public List<ProdutoRelatorioFaturamentoSumarizadoDTO> carregarRelatorioFaturamentoDetalhadoMensalPeriodo
            (String anoMesInicio, String anoMesTermino) {
        return relatorioService.carregarRelatorioFaturamentoDetalhadoMensalPeriodo(anoMesInicio, anoMesTermino);
    }

    @GetMapping(path="/detalhadoAnual/")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoSumarizadoDTO>> carregarRelatorioFaturamentoDetalhadoAnualNull() {
        List<ProdutoRelatorioFaturamentoSumarizadoDTO> produtoRelatorioFaturamentoSumarizadoDTO =
                relatorioService.carregarRelatorioFaturamentoDetalhadoAnualNull();
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoSumarizadoDTO);
    }

    @GetMapping(path="/detalhadoAnual/{anoFaturamento}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoSumarizadoDTO>> carregarRelatorioFaturamentoDetalhadoAnual
            (@PathVariable String anoFaturamento) {
        List<ProdutoRelatorioFaturamentoSumarizadoDTO> produtoRelatorioFaturamentoSumarizadoDTO =
                relatorioService.carregarRelatorioFaturamentoDetalhadoAnual(anoFaturamento);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoSumarizadoDTO);
    }

    @GetMapping(path="/detalhadoAnual/periodo/{anoInicial, anoTermino}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoSumarizadoDTO>> carregarRelatorioFaturamentoDetalhadoAnualPeriodo
            (String anoInicial, String anoTermino) {
        List<ProdutoRelatorioFaturamentoSumarizadoDTO> produtoRelatorioFaturamentoSumarizadoDTO =
                relatorioService.carregarRelatorioFaturamentoDetalhadoAnualPeriodo(anoInicial, anoTermino);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoSumarizadoDTO);
    }

    @GetMapping(path="/sumarizadoDiario/")
    public List<ProdutoRelatorioFaturamentoDetalhadoDTO> carregarRelatorioSumarizadoDetalhadoDiario() {
        return relatorioService.carregarRelatorioFaturamentoSumarizadoDiario();
    }

    @GetMapping(path="/sumarizadoDiario/periodo/{dataInicio, dataTermino}")
    public List<ProdutoRelatorioFaturamentoDetalhadoDTO> carregarRelatorioFaturamentoSumarizadoDiarioPeriodo
            (@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     LocalDate dataInicio,
             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     LocalDate dataTermino) {
        return relatorioService.carregarRelatorioFaturamentoSumarizadoDiarioPeriodo(dataInicio, dataTermino);
    }

    @GetMapping(path="/sumarizadoMensal/")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoDetalhadoDTO>> carregarRelatorioSumarizadoDetalhadoMensalNull() {
        List<ProdutoRelatorioFaturamentoDetalhadoDTO> produtoRelatorioFaturamentoDetalhadoDTO =
                relatorioService.carregarRelatorioFaturamentoSumarizadoMensalNull();
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoDetalhadoDTO);
    }

    @GetMapping(path="/sumarizadoMensal/{anoMesFaturamento}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoDetalhadoDTO>> carregarRelatorioSumarizadoDetalhadoMensal
            (@PathVariable String anoMesFaturamento) {
        List<ProdutoRelatorioFaturamentoDetalhadoDTO> produtoRelatorioFaturamentoDetalhadoDTO =
                relatorioService.carregarRelatorioFaturamentoSumarizadoMensal(anoMesFaturamento);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoDetalhadoDTO);
    }

    @GetMapping(path="/sumarizadoAnual/")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoDetalhadoDTO>> carregarRelatorioSumarizadoDetalhadoAnualNull() {
        List<ProdutoRelatorioFaturamentoDetalhadoDTO> produtoRelatorioFaturamentoDetalhadoDTO =
                relatorioService.carregarRelatorioFaturamentoSumarizadoAnualNull();
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoDetalhadoDTO);
    }

    @GetMapping(path="/sumarizadoMensal/periodo/{anoMesInicial, anoMesTermino}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoDetalhadoDTO>> carregarRelatorioSumarizadoDetalhadoMensalPeriodo
            (String anoMesInicial, String anoMesTermino) {
        List<ProdutoRelatorioFaturamentoDetalhadoDTO> produtoRelatorioFaturamentoDetalhadoDTO =
                relatorioService.carregarRelatorioFaturamentoSumarizadoMensalPeriodo(anoMesInicial, anoMesTermino);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoDetalhadoDTO);
    }

    @GetMapping(path="/sumarizadoAnual/{anoFaturamento}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoDetalhadoDTO>> carregarRelatorioSumarizadoDetalhadoAnual
            (@PathVariable String anoFaturamento) {
        List<ProdutoRelatorioFaturamentoDetalhadoDTO> produtoRelatorioFaturamentoDetalhadoDTO =
                relatorioService.carregarRelatorioFaturamentoSumarizadoAnual(anoFaturamento);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoDetalhadoDTO);
    }

    @GetMapping(path="/sumarizadoAnual/periodo/{anoInicial, anoTermino}")
    public ResponseEntity<List<ProdutoRelatorioFaturamentoDetalhadoDTO>> carregarRelatorioSumarizadoDetalhadoAnualPeriodo
            (String anoInicial, String anoTermino) {
        List<ProdutoRelatorioFaturamentoDetalhadoDTO> produtoRelatorioFaturamentoDetalhadoDTO =
                relatorioService.carregarRelatorioFaturamentoSumarizadoAnualPeriodo(anoInicial, anoTermino);
        return ResponseEntity.ok().body(produtoRelatorioFaturamentoDetalhadoDTO);
    }
}

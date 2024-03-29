package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.TipoPagamento;
import br.com.univesp.mercadocell.mercadocell.service.TipoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("tipoPagamento")
public class TipoPagamentoController {

    @Autowired
    private TipoPagamentoService tipoPagamentoService;

    @PostMapping
    public ResponseEntity<TipoPagamento> cadastrarTipoPagamento(@Valid @RequestBody TipoPagamento tipoPagamento) {
        tipoPagamentoService.cadastrarTipoPagamento(tipoPagamento);
        return ResponseEntity.accepted().body(tipoPagamento);
    }

    @GetMapping(path="/{idTipoPagamento}")
    public ResponseEntity<TipoPagamento> buscarTipoPagamentoPorId(@PathVariable int idTipoPagamento) {
        TipoPagamento  tipoPagamento = tipoPagamentoService.buscarTipoPagamentoPorId(idTipoPagamento);
        return ResponseEntity.ok().body(tipoPagamento);
    }

    @GetMapping
    public List<TipoPagamento> listarTiposPagamento() {
        return tipoPagamentoService.listarTiposPagamento();
    }

    @PutMapping
    public ResponseEntity<TipoPagamento> atualizarTipoPagamento(@Valid @RequestBody TipoPagamento tipoPagamento) {
        tipoPagamentoService.atualizarTipoPagamento(tipoPagamento);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idTipoPagamento}")
    public ResponseEntity<TipoPagamento> deletarTipoPagamento(@PathVariable int idTipoPagamento) {
        tipoPagamentoService.deletarTipoPagamento(idTipoPagamento);
        return ResponseEntity.noContent().build();
    }
}

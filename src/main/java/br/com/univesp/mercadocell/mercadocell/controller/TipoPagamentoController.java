package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.TipoPagamento;
import br.com.univesp.mercadocell.mercadocell.service.TipoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
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
        Optional<TipoPagamento> TipoPagamentoOpt =
                Optional.ofNullable(tipoPagamentoService.buscarTipoPagamentoPorId(idTipoPagamento));
        if (TipoPagamentoOpt.isPresent()){
            return new ResponseEntity<TipoPagamento>(TipoPagamentoOpt.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<TipoPagamento>(
                        new TipoPagamento(0, "Não Encontrado"), HttpStatus.OK
            );
        }
    }

    @GetMapping
    public List<TipoPagamento> listarTiposPagamento() {
        return tipoPagamentoService.listarTiposPagamento();
    }

    @PutMapping
    public ResponseEntity<?> atualizarTipoPagamento(@Valid @RequestBody TipoPagamento TipoPagamento) {
        tipoPagamentoService.atualizarTipoPagamento(TipoPagamento);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idTipoPagamento}")
    public ResponseEntity<?> deletarTipoPagamento(@PathVariable int idTipoPagamento) {
        tipoPagamentoService.deletarTipoPagamento(idTipoPagamento);
        return ResponseEntity.noContent().build();
    }
}

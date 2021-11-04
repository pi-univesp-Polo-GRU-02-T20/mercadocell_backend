package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.Operacao;
import br.com.univesp.mercadocell.mercadocell.service.OperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("operacao")
public class OperacaoController {

    @Autowired
    private OperacaoService operacaoService;

    @PostMapping
    public ResponseEntity<Operacao> cadastrarOperacao(@Valid @RequestBody Operacao operacao) {
        operacaoService.cadastrarOperacao(operacao);
        return ResponseEntity.accepted().body(operacao);
    }

    @GetMapping(path="/relatorios/operacao/id/{idOperacao}")
    public ResponseEntity<Operacao> buscarOperacaoPorId(@PathVariable int idOperacao) {
        Optional<Operacao> OperacaoOpt = Optional.ofNullable(operacaoService.buscarOperacaoPorId(idOperacao));
        if (OperacaoOpt.isPresent()){
            return new ResponseEntity<Operacao>(OperacaoOpt.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<Operacao>(new Operacao(), HttpStatus.OK);
        }
    }

    @GetMapping
    public List<Operacao> listarOperacoes() {
        return operacaoService.listarOperacoes();
    }

    @PutMapping
    public ResponseEntity<Operacao> atualizarOperacao(@Valid @RequestBody Operacao Operacao) {
        operacaoService.atualizarOperacao(Operacao);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idOperacao}")
    public ResponseEntity<Operacao> deletarOperacao(@PathVariable int idOperacao) {
        operacaoService.deletarOperacao(idOperacao);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path="/relatorios/operacao/pessoa")
    public List<Operacao>  listarOperacaoPorPessoa(@PathVariable int codPessoa, String tipoOperacao) {
        return operacaoService.listarOperacoesPorPessoa(codPessoa, tipoOperacao);
    }

    @GetMapping(path="/relatorios/operacao/periodo")
    public List<Operacao>  listarOperacoesPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                             LocalDate dataInicio,
                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                             LocalDate dataTermino) {
        return operacaoService.listarOperacoesPorPeriodo( dataInicio, dataTermino);
    }


}

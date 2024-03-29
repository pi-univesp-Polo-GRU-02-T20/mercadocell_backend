package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.Operacao;
import br.com.univesp.mercadocell.mercadocell.service.OperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
        Operacao operacao = operacaoService.buscarOperacaoPorId(idOperacao);
        return ResponseEntity.ok().body(operacao);
    }

    @GetMapping
    public List<Operacao> listarOperacoes() {
        return operacaoService.listarOperacoes();
    }

    @PutMapping
    public ResponseEntity<Operacao> atualizarOperacao(@RequestBody Operacao operacao) {
        operacaoService.atualizarOperacao(operacao);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idOperacao}")
    public ResponseEntity<Operacao> deletarOperacao(@PathVariable int idOperacao) {
        operacaoService.deletarOperacao(idOperacao);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path="/relatorios/operacao/pessoa")
    public List<Operacao>  listarOperacaoPorPessoa(@RequestParam int codPessoa,  @RequestParam String tipoOperacao) {
        return operacaoService.listarOperacoesPorPessoa(codPessoa, tipoOperacao);
    }

    @GetMapping(path="/relatorios/operacao/periodo")
    public List<Operacao>  listarOperacoesPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                             LocalDate dataInicio,
                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                             LocalDate dataTermino,
                                                     @RequestParam String tipoOperacao) {
        return operacaoService.listarOperacoesPorPeriodo( dataInicio, dataTermino, tipoOperacao);
    }
}

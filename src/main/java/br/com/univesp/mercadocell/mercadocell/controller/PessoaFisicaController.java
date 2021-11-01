package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.PessoaFisica;
import br.com.univesp.mercadocell.mercadocell.service.PessoaFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("pessoaFisica")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @PostMapping
    public ResponseEntity<PessoaFisica> cadastrarPessoaFisica(@Valid @RequestBody PessoaFisica pessoaFisica) {
        pessoaFisicaService.cadastrarPessoaFisica(pessoaFisica);
        return ResponseEntity.accepted().body(pessoaFisica);
    }

    @GetMapping(path="/{idPessoaFisica}")
    public ResponseEntity<PessoaFisica> buscarPessoaFisicaPorId(@PathVariable int idPessoaFisica) {
        Optional<PessoaFisica> PessoaFisicaOpt =
                    Optional.ofNullable(pessoaFisicaService.buscarPessoaFisicaPorId(idPessoaFisica));
        if (PessoaFisicaOpt.isPresent()){
            return new ResponseEntity<PessoaFisica>(PessoaFisicaOpt.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<PessoaFisica>(
                    new PessoaFisica(0, "Não Encontrado"),
                    HttpStatus.OK
            );
        }
    }

    @GetMapping(path="/buscar/{nomePessoaFisica}")
    public ResponseEntity<PessoaFisica> buscarPessoaFisicaPorNome(@PathVariable String nomePessoaFisica) {
        Optional<PessoaFisica> PessoaFisicaOpt =
                Optional.ofNullable(pessoaFisicaService.buscarPessoaFisicaPorNome(nomePessoaFisica));
        if (PessoaFisicaOpt.isPresent()){
            return new ResponseEntity<PessoaFisica>(PessoaFisicaOpt.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<PessoaFisica>(
                    new PessoaFisica(0, "Não Encontrado"),
                    HttpStatus.OK
            );
        }
    }

    @GetMapping
    public List<PessoaFisica> listarPessoasFisicas() {
        return pessoaFisicaService.listarPessoasFisicas();
    }

    @PutMapping
    public ResponseEntity<PessoaFisica> atualizarPessoaFisica(@Valid @RequestBody PessoaFisica pessoaFisica) {
        pessoaFisicaService.atualizarPessoaFisica(pessoaFisica);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idPessoaFisica}")
    public ResponseEntity<PessoaFisica> deletarPessoaFisica(@PathVariable int idPessoaFisica) {
        pessoaFisicaService.deletarPessoaFisica(idPessoaFisica);
        return ResponseEntity.noContent().build();
    }
}
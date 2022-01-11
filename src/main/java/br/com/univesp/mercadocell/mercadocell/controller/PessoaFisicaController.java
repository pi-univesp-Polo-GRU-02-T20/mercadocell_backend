package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.PessoaFisica;
import br.com.univesp.mercadocell.mercadocell.service.PessoaFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@CrossOrigin
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
        PessoaFisica pessoaFisica = pessoaFisicaService.buscarPessoaFisicaPorId(idPessoaFisica);
        return ResponseEntity.ok().body(pessoaFisica);
    }

    @GetMapping(path="/buscar/{nomePessoaFisica}")
    public ResponseEntity<PessoaFisica> buscarPessoaFisicaPorNome(@PathVariable String nomePessoaFisica) {
        PessoaFisica pessoaFisica = pessoaFisicaService.buscarPessoaFisicaPorNome(nomePessoaFisica);
        return ResponseEntity.ok().body(pessoaFisica);
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
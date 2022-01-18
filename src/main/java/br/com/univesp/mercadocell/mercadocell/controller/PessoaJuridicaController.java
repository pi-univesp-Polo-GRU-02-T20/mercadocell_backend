package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.PessoaJuridica;
import br.com.univesp.mercadocell.mercadocell.service.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("pessoaJuridica")
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @PostMapping
    public ResponseEntity<PessoaJuridica> cadastrarPessoaJuridica(@Valid @RequestBody PessoaJuridica pessoaJuridica) {
        pessoaJuridicaService.cadastrarPessoaJuridica(pessoaJuridica);
        return ResponseEntity.accepted().body(pessoaJuridica);
    }

    @GetMapping(path="/{idPessoaJuridica}")
    public ResponseEntity<PessoaJuridica> buscarPessoaJuridicaPorId(@PathVariable int idPessoaJuridica) {
        PessoaJuridica pessoaJuridica  = pessoaJuridicaService.buscarPessoaJuridicaPorId(idPessoaJuridica);
        return ResponseEntity.ok().body(pessoaJuridica);
    }

    @GetMapping(path="/buscar/{nomePessoaJuridica}")
    public ResponseEntity<PessoaJuridica> buscarPessoaJuridicaPorNome(@PathVariable String nomePessoaJuridica) {
        PessoaJuridica pessoaJuridica = pessoaJuridicaService.buscarPessoaJuridicaPorNome(nomePessoaJuridica);
        return ResponseEntity.ok().body(pessoaJuridica);
    }

    @GetMapping
    public List<PessoaJuridica> listarPessoasJuridicas() {
        return pessoaJuridicaService.listarPessoasJuridicas();
    }

    @PutMapping
    public ResponseEntity<PessoaJuridica> atualizarPessoaJuridica(@Valid @RequestBody PessoaJuridica pessoaJuridica) {
        pessoaJuridicaService.atualizarPessoaJuridica(pessoaJuridica);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idPessoaJuridica}")
    public ResponseEntity<PessoaJuridica> deletarPessoaJuridica(@PathVariable int idPessoaJuridica) {
        pessoaJuridicaService.deletarPessoaJuridica(idPessoaJuridica);
        return ResponseEntity.noContent().build();
    }
}

package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.PessoaJuridica;
import br.com.univesp.mercadocell.mercadocell.service.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
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
        Optional<PessoaJuridica> pessoaJuridicaOpt = Optional.ofNullable(pessoaJuridicaService.buscarPessoaJuridicaPorId(idPessoaJuridica));
        if (pessoaJuridicaOpt.isPresent()){
            return new ResponseEntity<PessoaJuridica>(pessoaJuridicaOpt.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<PessoaJuridica>(new PessoaJuridica(0, "Não Encontrado"), HttpStatus.OK);
        }
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

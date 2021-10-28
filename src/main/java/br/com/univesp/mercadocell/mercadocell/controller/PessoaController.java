package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.model.Pessoa;
import br.com.univesp.mercadocell.mercadocell.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService ;

    @PostMapping
    public ResponseEntity<Pessoa> Pessoa(@Valid @RequestBody Pessoa  pessoa) {
        pessoaService.cadastrarPessoa(pessoa);
        return ResponseEntity.accepted().body(pessoa);
    }

    @GetMapping(path="/{idPessoa}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable int idPessoa) {
        Optional<Pessoa> pessoaOpt =
                Optional.ofNullable(pessoaService.buscarPessoaPorId(idPessoa));
        if (pessoaOpt.isPresent()){
            return new ResponseEntity<Pessoa>(pessoaOpt.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<Pessoa>(
                    new Pessoa(0, "Não Encontrado",null, null, null
                    ), HttpStatus.OK
            );
        }
    }

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoaService.listarPessoas();
    }

    @PutMapping
    public ResponseEntity<Pessoa> atualizarPessoa(@Valid @RequestBody Pessoa  pessoa) {
        pessoaService.atualizarPessoas(pessoa);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idPessoa}")
    public ResponseEntity<Pessoa> deletarPessoa(@PathVariable int idPessoa) {
        pessoaService.deletarPessoa(idPessoa);
        return ResponseEntity.noContent().build();
    }

}

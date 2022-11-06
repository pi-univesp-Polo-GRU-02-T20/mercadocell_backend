package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.dto.PessoaFisicaDTO;
import br.com.univesp.mercadocell.mercadocell.model.Pessoa;
import br.com.univesp.mercadocell.mercadocell.model.PessoaFisica;
import br.com.univesp.mercadocell.mercadocell.service.PessoaFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public ResponseEntity<PessoaFisicaDTO> buscarPessoaFisicaPorId(@PathVariable int idPessoaFisica) {
        PessoaFisicaDTO pessoaFisicaDTO = convertePessoaFisicaParaPessoaFisicaDTO(
                        pessoaFisicaService.buscarPessoaFisicaPorId(idPessoaFisica)
        );
        return ResponseEntity.ok().body(pessoaFisicaDTO);
    }

    @GetMapping(path="/buscar/{nomePessoaFisica}")
    public ResponseEntity<PessoaFisicaDTO> buscarPessoaFisicaPorNome(@PathVariable String nomePessoaFisica) {
        PessoaFisicaDTO pessoaFisicaDTO =
                convertePessoaFisicaParaPessoaFisicaDTO(
                    pessoaFisicaService.buscarPessoaFisicaPorNome(nomePessoaFisica)
                );
        return ResponseEntity.ok().body(pessoaFisicaDTO);
    }

    @GetMapping
    public List<PessoaFisicaDTO> listarPessoasFisicas() {
        List<PessoaFisicaDTO> listaPessoasFisicasDTO = new ArrayList<PessoaFisicaDTO>();
        for(PessoaFisica pessoaFisica : pessoaFisicaService.listarPessoasFisicas()){
            listaPessoasFisicasDTO.add(convertePessoaFisicaParaPessoaFisicaDTO(pessoaFisica));
        }
        return listaPessoasFisicasDTO;
    }

    private PessoaFisicaDTO convertePessoaFisicaParaPessoaFisicaDTO(PessoaFisica pessoa) {
        return new PessoaFisicaDTO(
                pessoa.getCodPessoa(),
                pessoa.getNomePessoa(),
                pessoa.getDataNascimento(),
                pessoa.getEstadoNaturalidade()
        );
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
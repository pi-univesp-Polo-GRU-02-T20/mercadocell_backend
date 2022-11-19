package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.dto.PessoaJuridicaDTO;
import br.com.univesp.mercadocell.mercadocell.model.Pessoa;
import br.com.univesp.mercadocell.mercadocell.model.PessoaJuridica;
import br.com.univesp.mercadocell.mercadocell.service.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public ResponseEntity<PessoaJuridicaDTO> buscarPessoaJuridicaPorId(@PathVariable int idPessoaJuridica) {
        PessoaJuridicaDTO pessoaJuridicaDTO = convertePessoaJuridicaParaPessoaJuridicaDTO(
                pessoaJuridicaService.buscarPessoaJuridicaPorId(idPessoaJuridica)
        );
        return ResponseEntity.ok().body(pessoaJuridicaDTO);
    }

    @GetMapping(path="/buscar/{nomePessoaJuridica}")
    public ResponseEntity<PessoaJuridicaDTO> buscarPessoaJuridicaPorNome(@PathVariable String nomePessoaJuridica) {
        PessoaJuridicaDTO pessoaJuridicaDTO =
                convertePessoaJuridicaParaPessoaJuridicaDTO(
                    pessoaJuridicaService.buscarPessoaJuridicaPorNome(nomePessoaJuridica)
                );
        return ResponseEntity.ok().body(pessoaJuridicaDTO);
    }

    @GetMapping
    public List<PessoaJuridicaDTO> listarPessoasJuridicas() {
        List<PessoaJuridicaDTO> listaPessoasJuridicasDTO = new ArrayList<>();
            for(PessoaJuridica pessoaJuridica : pessoaJuridicaService.listarPessoasJuridicas()){
                listaPessoasJuridicasDTO.add(convertePessoaJuridicaParaPessoaJuridicaDTO(pessoaJuridica));
        }
        return listaPessoasJuridicasDTO;
    }

    private PessoaJuridicaDTO convertePessoaJuridicaParaPessoaJuridicaDTO(PessoaJuridica pessoa) {
        return new PessoaJuridicaDTO(
                pessoa.getCodPessoa(),
                pessoa.getNomePessoa()

        );
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

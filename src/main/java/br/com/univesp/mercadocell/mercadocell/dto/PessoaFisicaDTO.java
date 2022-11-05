package br.com.univesp.mercadocell.mercadocell.dto;

import br.com.univesp.mercadocell.mercadocell.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PessoaFisicaDTO extends Pessoa {

    private LocalDate dataNascimento;
    private String estadoNaturalidade;

    public PessoaFisicaDTO(Integer codPessoa, String nomePessoa,LocalDate dataNascimento, String estadoNaturalidade) {
        this.codPessoa = codPessoa;
        this.nomePessoa = nomePessoa;
        this.dataNascimento = dataNascimento;
        this.estadoNaturalidade = estadoNaturalidade;
    }
}

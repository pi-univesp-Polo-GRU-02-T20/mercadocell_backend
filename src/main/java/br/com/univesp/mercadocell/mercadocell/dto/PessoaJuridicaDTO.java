package br.com.univesp.mercadocell.mercadocell.dto;

import br.com.univesp.mercadocell.mercadocell.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import java.time.LocalDate;


@Data
@AllArgsConstructor
public class PessoaJuridicaDTO extends Pessoa {
    private String nomeRazaoSocial;
    private String codCNPJ;


    public PessoaJuridicaDTO(Integer codPessoa, String nomePessoa) {
        this.codPessoa = codPessoa;
        this.nomePessoa = nomePessoa;


    }
}
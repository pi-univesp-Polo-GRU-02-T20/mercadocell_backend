package br.com.univesp.mercadocell.mercadocell.dto;

import br.com.univesp.mercadocell.mercadocell.model.Pessoa;
import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;


@Data
public class PessoaJuridicaDTO extends Pessoa {
    private String nomeRazaoSocial;
    private String codCNPJ;

}

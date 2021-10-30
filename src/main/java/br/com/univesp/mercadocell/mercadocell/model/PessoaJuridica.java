package br.com.univesp.mercadocell.mercadocell.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PessoaJuridica extends Usuario {
    private Integer codPessoaJuridica;
    @NotEmpty(message = "A Razão Social deve ser preenchida")
    private String nomeRazaoSocial;

    public PessoaJuridica(Integer codPessoa, String nomePessoa, String login, String senha, Boolean ativo) {
        super(codPessoa, nomePessoa, login, senha, ativo);
    }

    public PessoaJuridica(final Integer codPessoa, final  String nomePessoa, final String login, String senha, Boolean ativo, Integer codPessoaJuridica, String nomeRazaoSocial, String nomeFantasia) {
        super(codPessoa, nomePessoa, login, senha, ativo);
        this.codPessoaJuridica = codPessoaJuridica;
        this.nomeRazaoSocial = nomeRazaoSocial;
    }
}

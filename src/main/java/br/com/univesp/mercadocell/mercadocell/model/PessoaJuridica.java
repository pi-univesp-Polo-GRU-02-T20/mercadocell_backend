package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


public class PessoaJuridica extends Pessoa{
    private Integer codPessoaJuridica;
    @NotEmpty(message = "A Razão Social deve ser preenchida")
    private String nomeRazaoSocial;
    private String nomeFantasia;

    public PessoaJuridica(Integer codPessoa, String nomeUsuario, String login, String senha, Boolean ativo, Integer codPessoaJuridica, String nomeRazaoSocial, String nomeFantasia) {
        super(codPessoa, nomeUsuario, login, senha, ativo);
        this.codPessoaJuridica = codPessoaJuridica;
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    public Integer getCodPessoaJuridica() {
        return codPessoaJuridica;
    }

    public void setCodPessoaJuridica(Integer codPessoaJuridica) {
        this.codPessoaJuridica = codPessoaJuridica;
    }

    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    public void setNomeRazaoSocial(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}

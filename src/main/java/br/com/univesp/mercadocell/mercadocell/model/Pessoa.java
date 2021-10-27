package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Pessoa {

    private Integer codPessoa;
    private String nomeUsuario;
    private String Login;
    private String senha;
    private Boolean ativo;
    @Autowired
    private List<Endereco> listaEndereco; //= new ArrayList<>();

    public Pessoa(Integer codPessoa, String nomeUsuario, String login, String senha, Boolean ativo) {
        this.codPessoa = codPessoa;
        this.nomeUsuario = nomeUsuario;
        Login = login;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Integer getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}

package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends Pessoa{

    private Integer codUsuario;
    private String login;
    private String senha;
    private Boolean ativo;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(Integer codUsuario, String login) {
        this.codUsuario = codUsuario;
        this.login = login;
    }

    public Usuario(Integer codPessoa, String nomePessoa, Integer codUsuario, String login, String senha, Boolean ativo) {
        super(codPessoa, nomePessoa);
        this.codUsuario = codUsuario;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
    }
}

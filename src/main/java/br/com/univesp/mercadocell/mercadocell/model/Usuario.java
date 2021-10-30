package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private Integer codUsuario;
    private String nomePessoa;
    private String login;
    private String senha;
    private Boolean ativo;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(Integer codUsuario, String nomePessoa) {
        this.codUsuario = codUsuario;
        this.nomePessoa = nomePessoa;
    }
}

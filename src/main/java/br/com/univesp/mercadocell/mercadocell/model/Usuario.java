package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
        super.setCodPessoa(codUsuario);
        this.login = login;
    }

    public Usuario(Integer codUsuario,  String nomePessoa,
                   String login, String senha, Boolean ativo, Integer codPessoa) {
        super(codPessoa, nomePessoa);
        this.codUsuario = codUsuario;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Usuario(Integer codUsuario, String login, String senha, Boolean ativo, Integer codPessoa) {
        super(codPessoa);
        this.codUsuario = codUsuario;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
    }
    public Usuario(Integer codUsuario,  String login, String senha, Boolean ativo) {
        this.codUsuario = codUsuario;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
    }
    @Override
    public String toString() {
        return "Usuario(codUsuario=" + this.getCodUsuario() + ", login=" +   this.getLogin() +
                ", ativo=" + this.getAtivo() + ", codPessoa="+ super.getCodPessoa() + ")";
    }
}

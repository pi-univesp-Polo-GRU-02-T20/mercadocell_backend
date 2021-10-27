package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


public class PessoaFisica extends Pessoa {
    private Integer codPessoaFisica;
    @NotEmpty(message = "O nome da pessoa Física deve ser preenchido")
    private String nomePessoaFisca;
    private Date dataNascimento;
    private String estadoNaturalidade;
    @NotEmpty(message = "O sexo da pessoa Física deve ser preenchido")
    private TipoSexo tipoSexo;

    public PessoaFisica(Integer codPessoa, String nomeUsuario, String login, String senha, Boolean ativo, Integer codPessoaFisica, String nomePessoaFisca, Date dataNascimento, String estadoNaturalidade, TipoSexo tipoSexo) {
        super(codPessoa, nomeUsuario, login, senha, ativo);
        this.codPessoaFisica = codPessoaFisica;
        this.nomePessoaFisca = nomePessoaFisca;
        this.dataNascimento = dataNascimento;
        this.estadoNaturalidade = estadoNaturalidade;
        this.tipoSexo = tipoSexo;
    }
}

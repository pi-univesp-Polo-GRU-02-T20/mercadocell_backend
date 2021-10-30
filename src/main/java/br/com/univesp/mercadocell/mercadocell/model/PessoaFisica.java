package br.com.univesp.mercadocell.mercadocell.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class PessoaFisica extends Usuario {
    private Integer codPessoaFisica;
    private Date dataNascimento;
    private String estadoNaturalidade;
    @NotEmpty(message = "O sexo da pessoa Física deve ser preenchido")
    private TipoSexo tipoSexo;

    public PessoaFisica(Integer codPessoa, String nomePessoa,
                        String estadoNaturalidade, Date dataNascimento,  TipoSexo tipoSexo) {
        super(codPessoa, nomePessoa);
        this.codPessoaFisica = codPessoaFisica;
        this.dataNascimento = dataNascimento;
        this.estadoNaturalidade = estadoNaturalidade;
        this.tipoSexo = tipoSexo;
    }
}

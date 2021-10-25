package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PessoaFisica {
    private Integer codPessoaFisica;
    @NotEmpty(message = "O nome da pessoa Física deve ser preenchido")
    private String nomePessoaFisca;
    private Date dataNascimento;
    private String estadoNaturalidade;
    @NotEmpty(message = "O sexo da pessoa Física deve ser preenchido")
    private TipoSexo tipoSexo;

}

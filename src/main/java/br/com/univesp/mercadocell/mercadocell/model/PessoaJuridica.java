package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class PessoaJuridica {
    private Integer codPessoaJuridica;
    @NotEmpty(message = "A Razão Social deve ser preenchida")
    private String nomeRazaoSocial;
    private String nomeFantasia;

}

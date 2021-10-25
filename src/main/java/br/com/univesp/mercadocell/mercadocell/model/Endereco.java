package br.com.univesp.mercadocell.mercadocell.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class Endereco {
    private Integer codEndereco;
    private Logradouro logradouro;
    private String descricaoComplemento;
    @NotEmpty(message = "O número deve ser preenchido")
    private Integer numeroEndereco;
    private String descricaoPontoReferencia;

}

package br.com.univesp.mercadocell.mercadocell.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class Endereco {
    private Integer codEndereco;
    private Logradouro logradouro;
    private String descricaoComplemento;
    @NotEmpty(message = "O número deve ser preenchido")
    private Integer numeroEndereco;
    private String descricaoPontoReferencia;

}

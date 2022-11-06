package br.com.univesp.mercadocell.mercadocell.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class Endereco {
    private Integer codEndereco;
    private String descricaoComplemento;
    @NotEmpty(message = "O número deve ser preenchido")
    private Integer numeroEndereco;
    private String descricaoPontoReferencia;
    private Integer codPessoa;
    private Logradouro logradouro;
}

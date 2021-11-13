package br.com.univesp.mercadocell.mercadocell.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeMedida {
    private Integer codUnidadeMedida;
    //@NotEmpty(message = "A Unidade de Medida deve ser preenchida")
    private String nomeUnidadeMedida;
    private String siglaUnidadeMedida;

    public UnidadeMedida(Integer codUnidadeMedida, String siglaUnidadeMedida) {
        this.codUnidadeMedida = codUnidadeMedida;
        this.siglaUnidadeMedida =  siglaUnidadeMedida;
    }

}

package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class UnidadeMedida {
    private Integer codUnidadeMedida;
    //@NotEmpty(message = "A Unidade de Medida deve ser preenchida")
    private String nomeUnidadeMedida;
    private String siglaUnidadeMedida;

    public UnidadeMedida(Integer codUnidadeMedida) {
        this.codUnidadeMedida = codUnidadeMedida;
    }
}

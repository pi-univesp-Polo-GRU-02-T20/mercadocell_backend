package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class Municipio {
    private Integer codMunicipio;
    @NotEmpty(message = "O nome do município deve ser preenchido")
    private String nomeMunicipio;
    private Estado estado;

}

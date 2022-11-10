package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class Municipio {
    private Integer codMunicipio;
    //@NotEmpty(message = "O nome do município deve ser preenchido")
    private String nomeMunicipio;
    private Estado estado;
}

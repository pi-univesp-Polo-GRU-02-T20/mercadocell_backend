package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class Bairro {
    private Integer codBairro;
    @NotEmpty(message = "O nome do bairro deve ser preenchido")
    private String nomeBairro;
    private Municipio municipio;
}

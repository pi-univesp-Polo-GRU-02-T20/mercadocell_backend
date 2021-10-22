package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class Categoria {

    private Integer codCategoria;

    @NotEmpty(message = "O nome da categoria deve ser preenchido")
    private String nomeCategoria;

}

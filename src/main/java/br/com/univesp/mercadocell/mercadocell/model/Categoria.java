package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    private Integer codCategoria;

    @NotEmpty(message = "O nome da categoria deve ser preenchido")
    private String nomeCategoria;

}

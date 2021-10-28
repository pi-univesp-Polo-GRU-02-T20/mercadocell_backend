package br.com.univesp.mercadocell.mercadocell.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubCategoria {
    private Integer  codSubCategoria;
    //@NotEmpty(message = "O nome da Subcategoria deve ser preenchida")
    private String  nomeSubCategoria;
    private Categoria Categoria;

}

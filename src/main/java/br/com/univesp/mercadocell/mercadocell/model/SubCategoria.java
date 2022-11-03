package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubCategoria {
    private Integer  codSubCategoria;
    //@NotEmpty(message = "O nome da Subcategoria deve ser preenchida")
    private String  nomeSubCategoria;
    private Categoria categoria;

    public SubCategoria(Integer codSubCategoria, String nomeSubCategoria) {
        this.codSubCategoria = codSubCategoria;
        this.nomeSubCategoria = nomeSubCategoria;
    }

    public SubCategoria(Integer codSubCategoria) {
        this.codSubCategoria = codSubCategoria;
    }
}

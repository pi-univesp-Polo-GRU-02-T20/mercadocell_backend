package br.com.univesp.mercadocell.mercadocell.model;
import lombok.*;

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

}

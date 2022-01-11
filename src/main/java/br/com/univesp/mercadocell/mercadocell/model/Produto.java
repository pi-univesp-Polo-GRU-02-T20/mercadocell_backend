package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private Integer codProduto;
    //@NotEmpty(message = "O nome do Produto deve ser preenchido")
    private String nomeProduto;
    private String descricaoProduto;
    private SubCategoria subCategoria;
    private UnidadeMedida unidadeMedida;

    public Produto(final Integer codProduto, final String nomeProduto, final String descricaoProduto,
                   final SubCategoria subCategoria, final UnidadeMedida unidadeMedida) {
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.subCategoria = subCategoria;
        this.unidadeMedida = unidadeMedida;
    }

}

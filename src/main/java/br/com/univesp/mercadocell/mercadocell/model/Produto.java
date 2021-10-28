package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Produto {

    private Integer codProduto;
    //@NotEmpty(message = "O nome do Produto deve ser preenchido")
    private String nomeProduto;
    private String descricaoProduto;
    private SubCategoria subCategoria;
    private UnidadeMedida unidadeMedida;

  /*  public Produto(final Integer codProduto, final String nomeProduto) {
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
    }
*/
}

package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class Produto {

    private Integer codProduto;
    @NotEmpty(message = "O nome do Produto deve ser preenchidos")
    private String nomeProduto;
    private String descricaoProduto;
    private SubCategoria subCategoria;
    private UnidadeMedida unidadeMedida;
}

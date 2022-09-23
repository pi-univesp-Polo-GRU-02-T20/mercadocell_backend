package br.com.univesp.mercadocell.mercadocell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoProdutoDTO {

    private String nomeProduto;
    private String descricaoProduto;
    private String nomeSubCategoria;
    private String nomeCategoria;
    private String nomeUnidadeMedida;
}

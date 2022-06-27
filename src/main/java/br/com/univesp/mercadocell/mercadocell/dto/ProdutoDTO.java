package br.com.univesp.mercadocell.mercadocell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProdutoDTO  {

    private Integer codProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private Integer codigoSubCategoria;
    private Integer codigoUnidadeMedida;
    private Integer quantidadeEstoqueMinima;
    private Integer quantidadeEstoqueMaxima;
    private Integer quantidadeEstoqueAtual;
    ProdutoDTO(){

    }
}

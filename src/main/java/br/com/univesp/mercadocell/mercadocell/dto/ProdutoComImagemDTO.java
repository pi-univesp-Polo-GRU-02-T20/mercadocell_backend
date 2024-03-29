package br.com.univesp.mercadocell.mercadocell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProdutoComImagemDTO {
    private Integer codProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private Integer codigoSubcategoria;
    private Integer codigoUnidadeMedida;
    private Integer quantidadeEstoqueMinimo;
    private Integer quantidadeEstoqueMaximo;
    private Integer quantidadeEstoqueAtual;
    private List<String> listaImagens;
/*
    public ProdutoDTO(Integer codProduto,String nomeProduto,String descricaoProduto,
                        Integer codigoSubcategoria,Integer codigoUnidadeMedida,
                        Integer quantidadeEstoqueMaximo,Integer quantidadeEstoqueMinimo,
                        Integer quantidadeEstoqueAtual)
    {
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto= descricaoProduto;
        this.codigoSubcategoria = codigoSubcategoria;
        this.codigoUnidadeMedida = codigoUnidadeMedida;
        this.quantidadeEstoqueAtual = quantidadeEstoqueAtual;
        this.quantidadeEstoqueMinimo = quantidadeEstoqueMinimo;
        this.quantidadeEstoqueMaximo = quantidadeEstoqueMaximo;
    }

    public ProdutoDTO(Integer codProduto,String nomeProduto,String descricaoProduto,
                      Integer codigoSubcategoria,Integer codigoUnidadeMedida,
                      Integer quantidadeEstoqueMaximo,Integer quantidadeEstoqueMinimo,
                      Integer quantidadeEstoqueAtual, List<Imagem> listaImagem)
    {
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto= descricaoProduto;
        this.codigoSubcategoria = codigoSubcategoria;
        this.codigoUnidadeMedida = codigoUnidadeMedida;
        this.quantidadeEstoqueAtual = quantidadeEstoqueAtual;
        this.quantidadeEstoqueMinimo = quantidadeEstoqueMinimo;
        this.quantidadeEstoqueMaximo = quantidadeEstoqueMaximo;
        this.listaImagem = listaImagem;
    }
*/
}

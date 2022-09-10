package br.com.univesp.mercadocell.mercadocell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    //TODO mudar cardinalidade produto 1:N imagem
    private MultipartFile arqImagem;
    //private List<Imagem> listaImagem;
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

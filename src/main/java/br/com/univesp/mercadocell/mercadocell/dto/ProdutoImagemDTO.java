package br.com.univesp.mercadocell.mercadocell.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProdutoImagemDTO {
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
}

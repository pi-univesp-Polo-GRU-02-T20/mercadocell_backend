package br.com.univesp.mercadocell.mercadocell.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProdutoInputDTO extends ProdutoDTO {

    //TODO mudar cardinalidade produto 1:N imagem
    private MultipartFile arqImagem;

    public ProdutoInputDTO(final Integer codProduto,final  String nomeProduto, final String descricaoProduto,
                           final Integer codigoSubCategoria,final  Integer codigoUnidadeMedida,
                           final Integer quantidadeEstoqueMinima,final  Integer quantidadeEstoqueMaxima,
                           final Integer quantidadeEstoqueAtual,final  MultipartFile arqImagem) {
        super(codProduto, nomeProduto, descricaoProduto, codigoSubCategoria, codigoUnidadeMedida,
                quantidadeEstoqueMinima, quantidadeEstoqueMaxima, quantidadeEstoqueAtual);
        this.arqImagem = arqImagem;
    }
    public ProdutoInputDTO(final Integer codProduto,final  String nomeProduto, final String descricaoProduto,
                           final Integer codigoSubCategoria,final  Integer codigoUnidadeMedida,
                           final Integer quantidadeEstoqueMinima,final  Integer quantidadeEstoqueMaxima,
                           final Integer quantidadeEstoqueAtual) {
        super(codProduto, nomeProduto, descricaoProduto, codigoSubCategoria, codigoUnidadeMedida,
                quantidadeEstoqueMinima, quantidadeEstoqueMaxima, quantidadeEstoqueAtual);
    }

}

package br.com.univesp.mercadocell.mercadocell.dto;

import br.com.univesp.mercadocell.mercadocell.model.Imagem;
import lombok.Data;
import lombok.Getter;

import java.util.List;
@Getter
public class ProdutoConsultaDTO  extends ProdutoDTO{

    private List<Imagem> listaImagem;

    public ProdutoConsultaDTO(final Integer codProduto,final  String nomeProduto, final String descricaoProduto,
                              final Integer codigoSubCategoria,final  Integer codigoUnidadeMedida,
                              final Integer quantidadeEstoqueMinima,final  Integer quantidadeEstoqueMaxima,
                              final Integer quantidadeEstoqueAtual,final  List<Imagem> listaImagem) {
        super(codProduto, nomeProduto, descricaoProduto, codigoSubCategoria, codigoUnidadeMedida,
                quantidadeEstoqueMinima, quantidadeEstoqueMaxima, quantidadeEstoqueAtual);
        this.listaImagem = listaImagem;
    }
    public ProdutoConsultaDTO(final Integer codProduto,final  String nomeProduto, final String descricaoProduto,
                              final Integer codigoSubCategoria,final  Integer codigoUnidadeMedida,
                              final Integer quantidadeEstoqueMinima,final  Integer quantidadeEstoqueMaxima,
                              final Integer quantidadeEstoqueAtual) {
        super(codProduto, nomeProduto, descricaoProduto, codigoSubCategoria, codigoUnidadeMedida,
                quantidadeEstoqueMinima, quantidadeEstoqueMaxima, quantidadeEstoqueAtual);
    }

/*
    public List<Imagem> getListaImagem() {
        return listaImagem;
    }

    public void setListaImagem(List<Imagem> listaImagem) {
        this.listaImagem = listaImagem;
    }

 */
}

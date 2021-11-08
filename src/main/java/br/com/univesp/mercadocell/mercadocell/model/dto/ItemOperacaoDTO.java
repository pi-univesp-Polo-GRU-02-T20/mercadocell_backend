package br.com.univesp.mercadocell.mercadocell.model.dto;

import br.com.univesp.mercadocell.mercadocell.model.ItemOperacao;
import br.com.univesp.mercadocell.mercadocell.model.Operacao;
import br.com.univesp.mercadocell.mercadocell.model.Produto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ItemOperacaoDTO extends ItemOperacao{

    private Operacao operacao;
    private Produto produto;

    public ItemOperacaoDTO(Integer codItemOperacao, Float quantidadeItem, Double valorItem,
                           Operacao operacao, Produto produto) {
        super(codItemOperacao, quantidadeItem, valorItem, produto.getCodProduto());
        this.operacao = operacao;
        this.produto = produto;
    }

}

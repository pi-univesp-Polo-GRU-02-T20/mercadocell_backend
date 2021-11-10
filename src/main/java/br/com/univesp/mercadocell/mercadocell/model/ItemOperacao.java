package br.com.univesp.mercadocell.mercadocell.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ItemOperacao {
    private Integer codItemOperacao;
    private Float quantidadeItem;
    private Double valorItem;
    private Operacao operacao;
    private Produto produto;

    public ItemOperacao(Integer codItemOperacao, Float quantidadeItem, Double valorItem,
                           Operacao operacao, Produto produto) {
        this.codItemOperacao = codItemOperacao;
        this.quantidadeItem = quantidadeItem;
        this.valorItem = valorItem;
        this.operacao = operacao;
        this.produto = produto;
    }
}

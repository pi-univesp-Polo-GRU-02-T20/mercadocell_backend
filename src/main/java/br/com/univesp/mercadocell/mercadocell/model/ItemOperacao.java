package br.com.univesp.mercadocell.mercadocell.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemOperacao {
    private Integer codItemOperacao;
    private Float quantidadeItem;
    private Double valorItem;
    private Integer codProduto;
    private Integer codOperacao;

    public ItemOperacao(Integer codItemOperacao, Float quantidadeItem, Double valorItem, Integer codProduto) {
        this.codItemOperacao = codItemOperacao;
        this.quantidadeItem = quantidadeItem;
        this.valorItem = valorItem;
        this.codProduto = codProduto;
    }
}

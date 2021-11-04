package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class ItemOperacao {
    private Integer codItemOperacao;
    private Float quantidadeItem;
    private Double valorItem;
    private Integer codProduto;
    private Integer codOperacao;
}

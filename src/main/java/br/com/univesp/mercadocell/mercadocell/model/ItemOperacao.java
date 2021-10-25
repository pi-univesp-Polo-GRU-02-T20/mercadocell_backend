package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemOperacao {
    private Integer codItemOperacao;
    private Integer quantidadeItem;
    private Float valorItem;
    private Produto produto;


}

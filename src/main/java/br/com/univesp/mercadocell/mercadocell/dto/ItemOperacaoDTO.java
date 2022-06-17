package br.com.univesp.mercadocell.mercadocell.dto;

import br.com.univesp.mercadocell.mercadocell.model.Operacao;
import br.com.univesp.mercadocell.mercadocell.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemOperacaoDTO {

    private Integer codItemOperacao;
    private Float quantidadeItem;
    private Double valorItem;
    private OperacaoDTO operacao;
    private ProdutoDTO produto;

}

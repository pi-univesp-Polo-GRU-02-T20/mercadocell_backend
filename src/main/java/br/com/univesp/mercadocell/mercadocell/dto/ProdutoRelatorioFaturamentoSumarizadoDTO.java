package br.com.univesp.mercadocell.mercadocell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class ProdutoRelatorioFaturamentoSumarizadoDTO {

    private Integer codigoProduto;
    private String nomeProduto;
    private Integer quantidadeItem;
    private Double valorPrecoMedio;
    private String descricaoPeriodo;

}




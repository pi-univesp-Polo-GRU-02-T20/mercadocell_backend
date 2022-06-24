package br.com.univesp.mercadocell.mercadocell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProdutoRelatorioFaturamentoDetalhadoDTO {

    private Integer codigoProduto;
    private String nomeProduto;
    private Double valorCustoVenda;
    private Double valorFaturado;
    private Double valorLiquido;
    private Double quantidadeItemEstoqueEntrada;
    private Double quantidadeItemEstoqueSaida;
    private String descricaoPeriodo;


}




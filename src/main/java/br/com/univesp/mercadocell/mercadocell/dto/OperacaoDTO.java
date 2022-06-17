package br.com.univesp.mercadocell.mercadocell.dto;

import br.com.univesp.mercadocell.mercadocell.model.Pessoa;
import br.com.univesp.mercadocell.mercadocell.model.TipoPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OperacaoDTO {
    private Integer codOperacao;
    private LocalDateTime dataOperacao;
    private String codNotaFiscal;
    private Double valorTotal;
    private Integer quantidadeParcela;
    private Boolean pago;
    private String tipoOperacao;
    private String tipoStatusOperacao;
    private Integer codigoPessoa;
    private Integer codigoTipoPagamento;
}

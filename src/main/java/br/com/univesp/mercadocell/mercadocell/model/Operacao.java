package br.com.univesp.mercadocell.mercadocell.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Operacao {
    private Integer codOperacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataOperacao;
    private String codNotaFiscal;
    private Double valorTotal;
    private Integer quantidadeParcela;
    private Boolean pago;
    private String tipoOperacao;
    private String tipoStatusOperacao;
    private Pessoa pessoa;
    private TipoPagamento tipoPagamento;

    public Operacao(Integer codOperacao, LocalDateTime dataOperacao, String codNotaFiscal, Double valorTotal,
                    Integer quantidadeParcela, Boolean pago, String tipoOperacao,
                    String tipoStatusOperacao, Pessoa pessoa, TipoPagamento tipoPagamento) {
        this.codOperacao = codOperacao;
        this.dataOperacao = dataOperacao;
        this.codNotaFiscal = codNotaFiscal;
        this.valorTotal = valorTotal;
        this.quantidadeParcela = quantidadeParcela;
        this.pago = pago;
        this.tipoOperacao = tipoOperacao;
        this.tipoStatusOperacao = tipoStatusOperacao;
        this.pessoa = pessoa;
        this.tipoPagamento = tipoPagamento;
    }

    public Operacao(Integer codOperacao, LocalDateTime dataOperacao, String codNotaFiscal,
                    Double valorTotal, Integer quantidadeParcela,
                    Boolean pago, String tipoOperacao,String tipoStatusOperacao) {
        this.codOperacao = codOperacao;
        this.dataOperacao = dataOperacao;
        this.codNotaFiscal = codNotaFiscal;
        this.valorTotal = valorTotal;
        this.quantidadeParcela = quantidadeParcela;
        this.pago = pago;
        this.tipoOperacao = tipoOperacao;
        this.tipoStatusOperacao = tipoStatusOperacao;
    }

    public Operacao(Integer codOperacao, LocalDateTime dataOperacao, String codNotaFiscal,
                    Double valorTotal, Integer quantidadeParcela, Boolean pago,
                    String tipoOperacao, TipoPagamento tipoPagamento) {
        this.codOperacao = codOperacao;
        this.dataOperacao = dataOperacao;
        this.codNotaFiscal = codNotaFiscal;
        this.valorTotal = valorTotal;
        this.quantidadeParcela = quantidadeParcela;
        this.pago = pago;
        this.tipoOperacao = tipoOperacao;
        this.tipoPagamento = tipoPagamento;
    }

    public Operacao(Integer codOperacao, LocalDateTime dataOperacao,
                    String codNotaFiscal, Double valorTotal, String tipoStatusOperacao, TipoPagamento tipoPagamento) {
        this.codOperacao = codOperacao;
        this.dataOperacao = dataOperacao;
        this.codNotaFiscal = codNotaFiscal;
        this.valorTotal = valorTotal;
        this.tipoStatusOperacao = tipoStatusOperacao;
        this.tipoPagamento = tipoPagamento;
    }
}


package br.com.univesp.mercadocell.mercadocell.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Operacao {
    private Integer codOperacao;

   // @NotEmpty(message = "A data da operação deve ser preenchida")
    //@DateTimeFormat(pattern = "yyyy-MM-dd't'HH:mm:ss", shape = JsonFormat.Shape.STRING /* iso = DateTimeFormat.ISO.DATE_TIME*/)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataOperacao;
    private String codNotaFiscal;
    private Float valorTotal;
    private Integer quantidadeParcela;
    private boolean pago;
    private String tipoOperacao;
    private String tipoStatusOperacao;
    private Integer codPessoa;
    @Autowired
    //private List<PagamentoOperacao> listaPagamentoOperacao; // = new ArrayList<>();

    public Operacao(Integer codOperacao, LocalDateTime dataOperacao, String codNotaFiscal, Float valorTotal,
                    Integer quantidadeParcela, boolean pago, String tipoOperacao,
                    String tipoStatusOperacao, Integer codPessoa) {
        this.codOperacao = codOperacao;
        this.dataOperacao = dataOperacao;
        this.codNotaFiscal = codNotaFiscal;
        this.valorTotal = valorTotal;
        this.quantidadeParcela = quantidadeParcela;
        this.pago = pago;
        this.tipoOperacao = tipoOperacao;
        this.tipoStatusOperacao = tipoStatusOperacao;
        this.codPessoa = codPessoa;
    }
}

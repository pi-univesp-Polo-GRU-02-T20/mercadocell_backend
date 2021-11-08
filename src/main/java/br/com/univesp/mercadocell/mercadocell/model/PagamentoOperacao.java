package br.com.univesp.mercadocell.mercadocell.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PagamentoOperacao {
    private Integer codPagamento;
    @NotEmpty(message = "O valor do pagamento deve ser preenchido")
    private Float valorPagamento;
    @NotEmpty(message = "A data do pagamento deve ser preenchida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataPagamento;
    @NotEmpty(message = "A data do vencimento deve ser preenchida")
    private Operacao operacao;
    private String tipoPagamento;

}

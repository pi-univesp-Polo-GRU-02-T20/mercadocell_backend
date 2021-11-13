package br.com.univesp.mercadocell.mercadocell.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoOperacao {
    private Integer codPagamento;
 //   @NotEmpty(message = "O valor do pagamento deve ser preenchido")
    private Double valorPagamento;
//    @NotEmpty(message = "A data do pagamento deve ser preenchida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataPagamento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataVencimento;
    private Operacao operacao;
}

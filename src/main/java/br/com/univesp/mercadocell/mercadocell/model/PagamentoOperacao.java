package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PagamentoOperacao {
    private Integer codPagamento;
    @NotEmpty(message = "O valor do pagamento deve ser preenchido")
    private Float valorPagamento;
    @NotEmpty(message = "A data do pagamento deve ser preenchida")
    private Date dataPagamento;
    @NotEmpty(message = "A data do vencimento deve ser preenchida")
    private Operacao operacao;
    private TipoPagamento tipoPagamento;
}

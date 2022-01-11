package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class TipoPagamento {
    private Integer codTipoPagamento;
    @NotEmpty(message = "O Tipo de Pagamento deve ser preenchido")
    private String nomeTipoPagamento;

}

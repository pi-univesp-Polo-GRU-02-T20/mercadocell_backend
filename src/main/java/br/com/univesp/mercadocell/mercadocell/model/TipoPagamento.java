package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class TipoPagamento {
    public Integer codTipoPagamento;
    @NotEmpty(message = "O Tipo de Pagamento deve ser preenchido")
    public String nomeTipoPagamento;

}

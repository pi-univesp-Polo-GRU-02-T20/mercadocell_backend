package br.com.univesp.mercadocell.mercadocell.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;


import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Operacao {
    private Integer codOperacao;
    @NotEmpty(message = "A data da operação deve ser preenchida")
    private Date dataOperacao;
    private Integer codNotaFiscal;
    private Float valorTotal;
    private Integer quantidadeParcela;
    private Usuario usuario;
    private boolean pago;
    private TipoOperacao tipoOperacao;
    private TipoStatusOperacao tipoStatusOperacao;
    @Autowired
    private List<PagamentoOperacao> listaPagamentoOperacao; // = new ArrayList<>();

}

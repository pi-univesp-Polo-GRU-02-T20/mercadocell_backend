package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estado {
    private Integer codEstado;
    @NotEmpty(message = "O nome do Estado deve ser preenchido")
    private String nomeEstado;
    @NotEmpty(message = "A Sigla deve ser preenchida")
    private String siglaUF;

    public Estado(Integer codEstado, String nomeEstado) {
        this.codEstado = codEstado;
        this.nomeEstado = nomeEstado;
    }

}

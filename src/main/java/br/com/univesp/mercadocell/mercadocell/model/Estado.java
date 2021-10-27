package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class Estado {
    public Estado(int i, String string) {
    }
    public Estado(int i, String string, Estado estado) {
    }
    private Integer codEstado;

    @NotEmpty(message = "O nome do Estado deve ser preenchido")
    private String NomeEstado;
    @NotEmpty(message = "A Sigla deve ser preenchida")
    private String siglaUF;


}

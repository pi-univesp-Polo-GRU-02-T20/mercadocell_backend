package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Logradouro {
    public Logradouro(int i, String string) {
    }
    private Integer codCEP;
    private Integer codBairro;
    private String descricaoLogradouro;
    private String descricaoComplemento;

}


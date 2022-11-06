package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Logradouro {

    private Integer codCEP;
    private String descricaoLogradouro;
    private String descricaoComplemento;
    private Bairro bairro;

}


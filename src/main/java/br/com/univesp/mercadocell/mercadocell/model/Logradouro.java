package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
public class Logradouro {

    private Integer codCEP;
    private String descricaoLogradouro;
    private String descricaoComplemento;
    private Bairro bairro;

}


package br.com.univesp.mercadocell.mercadocell.model;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
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
    private Bairro bairro;
    private String descricaoLogradouro;
    @Autowired
    private List<Endereco> listaEnderecos; // = new ArrayList<>();


}


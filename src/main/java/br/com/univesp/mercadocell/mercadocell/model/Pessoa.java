package br.com.univesp.mercadocell.mercadocell.model;

import lombok.Data;

@Data
public class Pessoa {


    private Integer codPessoa;
    private String nomePessoa;

    public Pessoa(Integer codPessoa, String nomePessoa) {
        this.codPessoa = codPessoa;
        this.nomePessoa = nomePessoa;
    }


    public Pessoa(){

    }

    public Pessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }
}

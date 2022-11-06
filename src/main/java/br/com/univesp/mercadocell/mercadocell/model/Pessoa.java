package br.com.univesp.mercadocell.mercadocell.model;

import lombok.Data;

@Data
public  class Pessoa {


    protected Integer codPessoa;
    protected String nomePessoa;
    protected Boolean flgConsentimentoDados = true;

    public Pessoa(Integer codPessoa, String nomePessoa) {
        this.codPessoa = codPessoa;
        this.nomePessoa = nomePessoa;
    }

    public Pessoa(Integer codPessoa, String nomePessoa, Boolean flgConsentimentoDados) {
        this.codPessoa = codPessoa;
        this.nomePessoa = nomePessoa;
        this.flgConsentimentoDados = flgConsentimentoDados;
    }

    public Pessoa(){

    }

    public Pessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }
}

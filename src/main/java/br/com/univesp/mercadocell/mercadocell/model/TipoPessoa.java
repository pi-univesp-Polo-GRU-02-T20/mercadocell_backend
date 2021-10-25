package br.com.univesp.mercadocell.mercadocell.model;

public enum TipoPessoa {
    FISICA('F'), JURIDICA('J');

    private final char tipoPessoa;
    TipoPessoa(char tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public char getTipoPessoa() {
        return tipoPessoa;
    }
}

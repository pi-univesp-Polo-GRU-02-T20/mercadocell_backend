package br.com.univesp.mercadocell.mercadocell.model;

public enum TipoSexo {

    MASCULINO('M'), FEMININO('F'), NEUTRO('N');

    private final char sglTipoSexo;

    TipoSexo(char sglTipoSexo) {
        this.sglTipoSexo = sglTipoSexo;
    }

    public char getSglTipoSexo() {
        return sglTipoSexo;
    }

}

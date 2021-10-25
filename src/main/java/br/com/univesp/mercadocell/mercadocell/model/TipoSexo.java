package br.com.univesp.mercadocell.mercadocell.model;

public enum TipoSexo {

    MASCULINO('M'), FEMININO('F'), NEUTRO('N');

    private final char tipoSexo;

    TipoSexo(char tipoSexo) {
        this.tipoSexo = tipoSexo;
    }

    public char getTipoSexo() {
        return tipoSexo;
    }

}

package br.com.univesp.mercadocell.mercadocell.old;

public enum TipoStatusOperacao {
    PEDIDO('P'), ORCAMENTO ('O');

    private final char tipoStatusOperacao;

    TipoStatusOperacao(char tipoStatusOperacao) {
        this.tipoStatusOperacao = tipoStatusOperacao;
    }

    public char getTipoStatus(){
        return tipoStatusOperacao;
    }
}

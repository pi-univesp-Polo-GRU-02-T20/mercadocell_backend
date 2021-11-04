package br.com.univesp.mercadocell.mercadocell.old;

public enum TipoOperacao {
    COMPRA('C'), VENDA('V');

    private final char tipoOperacao;
    TipoOperacao(char tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public char getTipoPessoa() {
        return tipoOperacao;
    }
}

package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Imagem {

    private Integer codigoImagem;
    private String nomeImagem;
    private String tipoImagem;
    private byte[] binarioImagem;

    public Imagem(String nomeImagem,String tipoImagem, byte[] binarioImagem){
        this.nomeImagem = nomeImagem;
        this.binarioImagem = binarioImagem;
        this.tipoImagem = tipoImagem;
    }
}



package br.com.univesp.mercadocell.mercadocell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImagemDTO {
    private Integer codigoImagem;
    private String nomeImagem;
    private String tipoImagem;

    public ImagemDTO( String nomeImagem, String tipoImagem){
        this.nomeImagem = nomeImagem;
        this.tipoImagem = tipoImagem;
    }
}

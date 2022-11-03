package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bairro {
    private Integer codBairro;
    @NotEmpty(message = "O nome do bairro deve ser preenchido")
    private String nomeBairro;
    private Integer codMunicipio;

    public Bairro(Integer codBairro, String nomeBairro) {
        this.codBairro = codBairro;
        this.nomeBairro = nomeBairro;
    }

}

package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Pessoa {

    private Integer codPessoa;
    private String nomePessoa;
    private String Login;
    private String senha;
    private Boolean ativo;
    private final List<Endereco> listaEndereco  = new ArrayList<>();

    public Pessoa() {

    }
}

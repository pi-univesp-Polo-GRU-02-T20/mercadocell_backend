package br.com.univesp.mercadocell.mercadocell.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Pessoa extends Usuario{

    @Autowired
    private  List<Endereco> listaEndereco ;

    public Pessoa(Integer codPessoa, String nomePessoa, String Login, String senha, Boolean ativo) {
        super(codPessoa, nomePessoa, Login, senha, ativo);
    }
}

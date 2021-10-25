package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Pessoa {

    private Integer codPessoa;
    private String nomeUsuario;
    private String Login;
    private String senha;
    private Boolean ativo;
    @Autowired
    private List<Endereco> listaEndereco; //= new ArrayList<>();


}

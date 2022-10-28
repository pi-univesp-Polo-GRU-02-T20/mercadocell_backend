package br.com.univesp.mercadocell.mercadocell.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsuarioDTOLogin {

    private String login;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

}

package br.com.univesp.mercadocell.mercadocell.dto.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignupRequestDTO {
    private Integer codUsuario;
    private String login;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    //private Set<String> role;


    /*
    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }*/
}

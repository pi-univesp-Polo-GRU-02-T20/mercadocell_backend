package br.com.univesp.mercadocell.mercadocell.dto.jwt;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDTO {
    @NotBlank
    private String login;

    @NotBlank
    private String senha;


}

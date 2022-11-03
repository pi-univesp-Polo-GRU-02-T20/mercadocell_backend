package br.com.univesp.mercadocell.mercadocell.dto.jwt;
import lombok.Data;

@Data
public class JwtResponseDTO {
    private Integer id;
    private String login;
    private String token;
    private String type = "Bearer";

    public JwtResponseDTO(Integer id, String login, String token){
        this.id = id;
        this.login = login;
        this.token = token;
    }
    //private String email;
   // private List<String> roles;

}

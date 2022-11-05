package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaJuridica extends Pessoa {

  //@NotEmpty(message = "A Razão Social deve ser preenchida")
    private String nomeRazaoSocial;
    private String codCNPJ;
    private Endereco endereco;

    public PessoaJuridica(Integer codPessoa, String nomePessoa,
                          String nomeRazaoSocial, String codCNPJ) {
        super(codPessoa, nomePessoa);
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.codCNPJ = codCNPJ;
    }

}

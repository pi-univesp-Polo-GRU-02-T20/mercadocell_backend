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

    public PessoaJuridica(final Integer codPessoa, final String nomePessoa,
                          final String nomeRazaoSocial, final String codCNPJ, final Boolean flgConsentimentoDados) {
        super(codPessoa, nomePessoa, flgConsentimentoDados);
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.codCNPJ = codCNPJ;
        this.flgConsentimentoDados = flgConsentimentoDados;
    }

    public PessoaJuridica(final Integer codPessoa,final  String nomePessoa) {
        super(codPessoa, nomePessoa);
    }
}

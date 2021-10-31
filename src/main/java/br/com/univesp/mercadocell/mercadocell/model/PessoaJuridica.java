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
    private Integer codPessoaJuridica;
    //@NotEmpty(message = "A Razão Social deve ser preenchida")
    private String nomeRazaoSocial;
    private String codCNPJ;
/*  //todo -> Verificar como associar dados de Endereço e Operação à pessoa
    @Autowired
    private List<Endereco> listaEndereco;
    @Autowired
    private List<Operacao> listaOperacao;
*/

   /* public PessoaJuridica(Integer codPessoa, String nomePessoa, String login, String senha, Boolean ativo) {
        super(codPessoa, nomePessoa, login, senha, ativo);
    }

    public PessoaJuridica(  // atributos do usuário
                            final Integer codPessoa, final  String nomePessoa, final String login, final String senha,
                          final Boolean ativo,
                          // atributos da pessoa jurídica
                          final Integer codPessoaJuridica, final String nomeRazaoSocial) {
        super(codPessoa, nomePessoa, login, senha, ativo);
        this.codPessoaJuridica = codPessoaJuridica;
        this.nomeRazaoSocial = nomeRazaoSocial;
    }

    public PessoaJuridica(Integer codUsuario, String nomePessoa, Integer codPessoaJuridica, String nomeRazaoSocial) {
        super(codUsuario, nomePessoa);
        this.codPessoaJuridica = codPessoaJuridica;
        this.nomeRazaoSocial = nomeRazaoSocial;
    }*/

    public PessoaJuridica(Integer codPessoa, String nomePessoa, Integer codPessoaJuridica,
                          String nomeRazaoSocial, String codCNPJ) {
        super(codPessoa, nomePessoa);
        this.codPessoaJuridica = codPessoaJuridica;
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.codCNPJ = codCNPJ;
    }

    public PessoaJuridica(Integer codPessoaJuridica, String nomeRazaoSocial) {
        this.codPessoaJuridica = codPessoaJuridica;
        this.nomeRazaoSocial = nomeRazaoSocial;
    }
}

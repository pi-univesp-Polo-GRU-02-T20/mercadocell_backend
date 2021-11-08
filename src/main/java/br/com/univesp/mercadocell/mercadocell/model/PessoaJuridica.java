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
/*  todo -> Verificar como associar dados de Endereço e Operação à pessoa
    @Autowired
    private List<Endereco> listaEndereco;
    @Autowired
    private List<Operacao> listaOperacao;
*/

    public PessoaJuridica(Integer codPessoa, String nomePessoa, Integer codPessoaJuridica,
                          String nomeRazaoSocial, String codCNPJ) {
        super(codPessoa, nomePessoa);
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.codCNPJ = codCNPJ;
    }

}

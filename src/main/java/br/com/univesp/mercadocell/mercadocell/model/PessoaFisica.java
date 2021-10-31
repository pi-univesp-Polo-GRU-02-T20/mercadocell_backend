package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFisica extends Pessoa {

    private Date dataNascimento;
    private String estadoNaturalidade;
   // @NotEmpty(message = "O sexo da pessoa Física deve ser preenchido")

    private char tipoSexo;
    //private TipoSexo tipoSexo;

/*  //todo -> Verificar como associar dados de Endereço e Operação à pessoa
    @Autowired
    private List<Endereco> listaEndereco;
    @Autowired
    private List<Operacao> listaOperacao;
*/
    public PessoaFisica(final Integer codPessoa,final  String nomePessoa, final Date dataNascimento,
                        final String estadoNaturalidade,final  char tipoSexo) {
        super(codPessoa, nomePessoa);
        this.dataNascimento = dataNascimento;
        this.estadoNaturalidade = estadoNaturalidade;
        this.tipoSexo = tipoSexo;
    }

    public PessoaFisica(final Integer codPessoa,final  String nomePessoa) {
        super(codPessoa, nomePessoa);
    }
}

package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFisica extends Pessoa {

    private LocalDate dataNascimento;
    private String estadoNaturalidade;
    private Endereco endereco;
   // @NotEmpty(message = "O sexo da pessoa Física deve ser preenchido")

    private String tipoSexo;
    //private TipoSexo tipoSexo;

/*  //todo -> Verificar como associar dados de Endereço e Operação à pessoa
    @Autowired
    private List<LocalDateEndereco> listaEndereco;
    @Autowired
    private List<Operacao> listaOperacao;
*/
    public PessoaFisica(final Integer codPessoa,final  String nomePessoa, final LocalDate dataNascimento,
                        final String estadoNaturalidade,final  String tipoSexo, final Boolean flgConsentimentoDados) {
        super(codPessoa, nomePessoa, flgConsentimentoDados);
        this.dataNascimento = dataNascimento;
        this.estadoNaturalidade = estadoNaturalidade;
        this.tipoSexo = tipoSexo;
        this.flgConsentimentoDados = flgConsentimentoDados;
    }

    public PessoaFisica(final Integer codPessoa,final  String nomePessoa) {
        super(codPessoa, nomePessoa);
    }
}

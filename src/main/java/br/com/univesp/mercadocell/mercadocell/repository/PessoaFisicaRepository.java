package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.PessoaFisica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
// EndPoint (API) -> Controller -> Service -> Repository -> BD
@Repository
public class PessoaFisicaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_PESSOA_FISICA =
            "SELECT P.COD_PESSOA, P.NME_PESSOA, PF.SGL_UF_NATURALIDADE, PF.DTA_NASCIMENTO, PF.TPO_SEXO, PF.FLG_CONSENTIMENTO_DADOS " +
                " FROM PESSOA P INNER JOIN PESSOA_FISICA PF ON P.COD_PESSOA = PF.COD_PESSOA ";
    private static final String COLUNA_COD_PESSOA = "COD_PESSOA";
    private static final String COLUNA_SGL_UF_NATURALIDADE = "SGL_UF_NATURALIDADE";
    private static final String COLUNA_DTA_NASCIMENTO  = "DTA_NASCIMENTO";
    private static final String COLUNA_TPO_SEXO  = "TPO_SEXO";
    private static final String COLUNA_NME_PESSOA = "NME_PESSOA";
    private static final String COLUNA_FLG_CONSENTIMENTO_DADOS = "FLG_CONSENTIMENTO_DADOS";

    @Transactional
    public void cadastrarPessoaFisica(PessoaFisica  pessoaFisica) {
        final String INSERT_PESSOA_FISICA = "INSERT INTO PESSOA (NME_PESSOA, FLG_CONSENTIMENTO_DADOS) VALUES (?, ?); ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(INSERT_PESSOA_FISICA,
                        new String[]{   COLUNA_COD_PESSOA,
                                        COLUNA_FLG_CONSENTIMENTO_DADOS
                                    });
                statement.setString(1, pessoaFisica.getNomePessoa());
                statement.setBoolean(1, pessoaFisica.getFlgConsentimentoDados());
                return statement;
            }
        }, keyHolder);

       jdbcTemplate.update(
                "INSERT INTO PESSOA_FISICA (COD_PESSOA, SGL_UF_NATURALIDADE, DTA_NASCIMENTO, TPO_SEXO ) " +
                        " VALUES (?, ?, ?, ?)",
                       keyHolder.getKey().intValue(),//nosonar
                        pessoaFisica.getEstadoNaturalidade(),
                        pessoaFisica.getDataNascimento().toString(),
                        pessoaFisica.getTipoSexo()
        );
    }

    public PessoaFisica buscarPessoaFisicaPorId(int idPessoaFisica) {
        return jdbcTemplate.queryForObject(
                SELECT_PESSOA_FISICA + "WHERE PF.COD_PESSOA = ? "
                , (resultSet, rowNum) ->
                        new PessoaFisica(
                                resultSet.getInt(COLUNA_COD_PESSOA),
                                resultSet.getString(COLUNA_NME_PESSOA),
                                resultSet.getObject(COLUNA_DTA_NASCIMENTO, LocalDate.class),
                                resultSet.getString(COLUNA_SGL_UF_NATURALIDADE),
                                resultSet.getString(COLUNA_TPO_SEXO),
                                resultSet.getBoolean(COLUNA_FLG_CONSENTIMENTO_DADOS)
                                //TipoSexo.valueOf(rs.getString("TPO_SEXO"))
                        ),
                idPessoaFisica
        );
    }
    public PessoaFisica buscarPessoaFisicaPorNome(String nomePessoaFisica) {
        return jdbcTemplate.queryForObject(
                SELECT_PESSOA_FISICA + "WHERE P.NME_PESSOA LIKE ?"
                , (resultSet, rowNum) ->
                        new PessoaFisica(
                                resultSet.getInt(COLUNA_COD_PESSOA),
                                resultSet.getString(COLUNA_NME_PESSOA),
                                resultSet.getObject(COLUNA_DTA_NASCIMENTO, LocalDate.class),
                                resultSet.getString(COLUNA_SGL_UF_NATURALIDADE),
                                resultSet.getString(COLUNA_TPO_SEXO),
                                resultSet.getBoolean(COLUNA_FLG_CONSENTIMENTO_DADOS)
                                //TipoSexo.valueOf(rs.getString("TPO_SEXO"))
                        ),
                "%" + nomePessoaFisica + "%"
        );
    }
    public List<PessoaFisica> listarPessoasFisicas() {
        return jdbcTemplate.query(SELECT_PESSOA_FISICA
                , (resultSet, rowNum) ->
                        new PessoaFisica(
                                resultSet.getInt(COLUNA_COD_PESSOA),
                                resultSet.getString(COLUNA_NME_PESSOA),
                                resultSet.getObject(COLUNA_DTA_NASCIMENTO, LocalDate.class),
                                resultSet.getString(COLUNA_SGL_UF_NATURALIDADE),
                                resultSet.getString(COLUNA_TPO_SEXO),
                                resultSet.getBoolean(COLUNA_FLG_CONSENTIMENTO_DADOS)
                                //TipoSexo.valueOf(rs.getString("TPO_SEXO"))
                        )
        );
    }

    @Transactional
    public void atualizarPessoaFisica(PessoaFisica pessoaFisica) {
        jdbcTemplate.update(
                "UPDATE PESSOA SET NME_PESSOA, FLG_CONSENTIMENTO_DADOS = ?" +
                        " WHERE COD_PESSOA = ?",
                pessoaFisica.getNomePessoa(),
                pessoaFisica.getCodPessoa(),
                pessoaFisica.getFlgConsentimentoDados()
        );
        jdbcTemplate.update(
                "UPDATE PESSOA_FISICA SET SGL_UF_NATURALIDADE = ?, DTA_NASCIMENTO = ?, TPO_SEXO = ?" +
                        " WHERE COD_PESSOA = ?",
                    pessoaFisica.getEstadoNaturalidade(),
                    pessoaFisica.getDataNascimento(),
                    pessoaFisica.getTipoSexo(),//getTipoSexo().getSglTipoSexo(),
                    pessoaFisica.getCodPessoa()
        );
        //TODO arrumar update de endereco de pessoa fisica
        jdbcTemplate.update(
                "UPDATE ENDERECO SET DSC_COMPLEMENTO = ?, NRO_ENDERECO = ?, DSC_PONTO_REFERENCIA = ?, COD_CEP = ? " +
                        " WHERE COD_PESSOA = ? AND COD_ENDERECO = ?",
                    pessoaFisica.getEndereco().getDescricaoComplemento(),
                    pessoaFisica.getEndereco().getNumeroEndereco(),
                    pessoaFisica.getEndereco().getDescricaoPontoReferencia(),
                    pessoaFisica.getEndereco().getLogradouro().getCodCEP(),
                    pessoaFisica.getCodPessoa(),
                    pessoaFisica.getEndereco().getCodEndereco()
        );
    }

    @Transactional
    public void deletarPessoaFisica(int idPessoaFisica) throws DataIntegrityViolationException {
        jdbcTemplate.update(
                "DELETE FROM ENDERECO WHERE COD_PESSOA = ? ",
                idPessoaFisica
        );
        jdbcTemplate.update(
                "DELETE FROM PESSOA_FISICA WHERE COD_PESSOA = ? ",
                idPessoaFisica
        ); 
        jdbcTemplate.update(
                "DELETE FROM PESSOA WHERE COD_PESSOA = ? ",
                idPessoaFisica
        );
    }


}

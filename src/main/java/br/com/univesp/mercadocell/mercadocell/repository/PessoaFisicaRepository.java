package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.PessoaFisica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
// EndPoint (API) -> Controller -> Service -> Repository -> BD
@Repository
public class PessoaFisicaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_PESSOA_FISICA =
            "SELECT P.COD_PESSOA, P.NME_PESSOA, PF.SGL_UF_NATURALIDADE, PF.DTA_NASCIMENTO, PF.TPO_SEXO " +
                " FROM PESSOA P INNER JOIN PESSOA_FISICA PF ON P.COD_PESSOA = PF.COD_PESSOA ";
    @Transactional
    public void cadastrarPessoaFisica(PessoaFisica  pessoaFisica) {
        final String INSERT_PESSOA_FISICA = "INSERT INTO PESSOA (NME_PESSOA) VALUES (?); ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(INSERT_PESSOA_FISICA,
                        new String[]{"COD_PESSOA"});
                statement.setString(1, pessoaFisica.getNomePessoa());
                return statement;
            }
        }, keyHolder);
        int codPessoa  = keyHolder.getKey().intValue();
       jdbcTemplate.update(
                "INSERT INTO `PESSOA_FISICA` (COD_PESSOA, SGL_UF_NATURALIDADE, DTA_NASCIMENTO, TPO_SEXO ) " +
                        " VALUES (?, ?, ?, ?)",
                        codPessoa,
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
                                resultSet.getInt("COD_PESSOA"),
                                resultSet.getString("NME_PESSOA"),
                                resultSet.getObject("DTA_NASCIMENTO", LocalDate.class),
                                resultSet.getString("SGL_UF_NATURALIDADE"),
                                resultSet.getString("TPO_SEXO")
                                //TipoSexo.valueOf(rs.getString("TPO_SEXO"))
                        ),
                new Object[]{idPessoaFisica}
        );
    }
    public PessoaFisica buscarPessoaFisicaPorNome(String nomePessoaFisica) {
        return jdbcTemplate.queryForObject(
                SELECT_PESSOA_FISICA + "WHERE P.NME_PESSOA LIKE ?"
                , (resultSet, rowNum) ->
                        new PessoaFisica(
                                resultSet.getInt("COD_PESSOA"),
                                resultSet.getString("NME_PESSOA"),
                                resultSet.getObject("DTA_NASCIMENTO", LocalDate.class),
                                resultSet.getString("SGL_UF_NATURALIDADE"),
                                resultSet.getString("TPO_SEXO")
                                //TipoSexo.valueOf(rs.getString("TPO_SEXO"))
                        ),
                new Object[]{"%" + nomePessoaFisica + "%"}
        );
    }
    public List<PessoaFisica> listarPessoasFisicas() {
        return jdbcTemplate.query(SELECT_PESSOA_FISICA
                , (resultSet, rowNum) ->
                        new PessoaFisica(
                                resultSet.getInt("COD_PESSOA"),
                                resultSet.getString("NME_PESSOA"),
                                resultSet.getObject("DTA_NASCIMENTO", LocalDate.class),
                                resultSet.getString("SGL_UF_NATURALIDADE"),
                                resultSet.getString("TPO_SEXO")//TipoSexo.valueOf(resultSet.getString("TPO_SEXO"))
                        )
        );
    }

    @Transactional
    public void atualizarPessoaFisica(PessoaFisica pessoaFisica) {
        jdbcTemplate.update(
                "UPDATE PESSOA SET NME_PESSOA = ?" +
                        " WHERE COD_PESSOA = ?",
                pessoaFisica.getNomePessoa(),
                pessoaFisica.getCodPessoa()
        );
        jdbcTemplate.update(
                "UPDATE PESSOA_FISICA SET SGL_UF_NATURALIDADE = ?, DTA_NASCIMENTO = ?, TPO_SEXO = ?" +
                        " WHERE COD_PESSOA = ?",
                    pessoaFisica.getEstadoNaturalidade(),
                    pessoaFisica.getDataNascimento(),
                    pessoaFisica.getTipoSexo(),//getTipoSexo().getSglTipoSexo(),
                    pessoaFisica.getCodPessoa()
        );
    }

    @Transactional
    public void deletarPessoaFisica(int idPessoaFisica) throws DataIntegrityViolationException {
        jdbcTemplate.update(
                "DELETE FROM `PESSOA_FISICA` WHERE `COD_PESSOA` = ? ",
                idPessoaFisica
        ); 
        jdbcTemplate.update(
                "DELETE FROM `PESSOA` WHERE `COD_PESSOA` = ? ",
                idPessoaFisica
        );
    }
}

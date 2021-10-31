package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.PessoaFisica;
import br.com.univesp.mercadocell.mercadocell.model.TipoSexo;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

@Repository
public class PessoaFisicaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    KeyHolder keyHolder ;// = new GeneratedKeyHolder();
    //KeyHolder keyHolder = new GeneratedKeyHolder();

    private static final String SELECT_PESSOA_FISICA =
            "SELECT P.COD_PESSOA, P.NME_PESSOA, PF.SGL_UF_NATURALIDADE, PF.DTA_NASCIMENTO, PF.TPO_SEXO " +
                " FROM PESSOA P INNER JOIN PESSOA_FISICA PF WHERE P.COD_PESSOA = PF.COD_PESSOA ";
   /*
    public void cadastrarPessoaFisica(PessoaFisica  pessoaFisica) {

        jdbcTemplate.update(
                "INSERT INTO `PESSOA_FISICA` (COD_USUARIO, SGL_UF_NATURALIDADE, DTA_NASCIMENTO, TPO_SEXO ) " +
                        " VALUES (?, ?, ?, ?)",
                null,
                pessoaFisica.getEstadoNaturalidade(),
                pessoaFisica.getDataNascimento(),
                pessoaFisica.getTipoSexo().getSglTipoSexo()
        );
    }*/
    //todo ajustar trecho de cadastro de pessoa física - > erro ao retornar ID de PESSOA, para cadastrar na tb filha

    @Transactional
    public void cadastrarPessoaFisica(PessoaFisica  pessoaFisica) {

        final String INSERT_PESSOA_FISICA = "INSERT INTO PESSOA (NME_PESSOA) VALUES (?); ";
/*
        https://stackoverflow.com/questions/35088885/how-to-get-inserted-id-using-spring-jdbctemplate-updatestring-sql-obj-args
        KeyHolder keyHolder = new GeneratedKeyHolder();
        //final String INSERT_PESSOA_FISICA = "INSERT INTO PESSOA (NME_PESSOA) VALUES (?);select LAST_INSERT_ID(); ";
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        INSERT_PESSOA_FISICA, new String[] { "COD_PESSOA" }
                );
                ps.setString(1, pessoaFisica.getNomePessoa());
                return ps;
            }
        }, keyHolder);
*/

         GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(INSERT_PESSOA_FISICA, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, pessoaFisica.getNomePessoa());
                return statement;
            }
        }, holder);
        int codPessoa  = keyHolder.getKey().intValue();

        jdbcTemplate.update(
                "INSERT INTO `PESSOA_FISICA` (COD_PESSOA, SGL_UF_NATURALIDADE, DTA_NASCIMENTO, TPO_SEXO ) " +
                        " VALUES (?, ?, ?, ?)",
                        codPessoa,
                        pessoaFisica.getEstadoNaturalidade(),
                        pessoaFisica.getDataNascimento(),
                        pessoaFisica.getTipoSexo()//.getSglTipoSexo()
        );
    }

    public PessoaFisica buscarPessoaFisicaPorId(int idPessoaFisica) {
        try {
            return jdbcTemplate.queryForObject(
                    SELECT_PESSOA_FISICA + "WHERE COD_PESSOA = ? "
                    , (rs, rowNum) ->
                            new PessoaFisica(
                                    rs.getInt("COD_PESSOA"),
                                    rs.getString("NME_PESSOA"),
                                    rs.getDate("DTA_NASCIMENTO"),
                                    rs.getString("SGL_UF_NATURALIDADE"),
                                    rs.getString("TPO_SEXO").charAt(0)//TipoSexo.valueOf(rs.getString("TPO_SEXO"))
                            ),
                    new Object[]{idPessoaFisica}
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<PessoaFisica> listarPessoasFisicas() {
        return jdbcTemplate.query(SELECT_PESSOA_FISICA
                , (rs, rowNum) ->
                        new PessoaFisica(
                                rs.getInt("COD_PESSOA"),
                                rs.getString("NME_PESSOA"),
                                rs.getDate("DTA_NASCIMENTO"),
                                rs.getString("SGL_UF_NATURALIDADE"),
                                rs.getString("TPO_SEXO").charAt(0)//TipoSexo.valueOf(rs.getString("TPO_SEXO"))
                        )
        );
    }

    @Transactional
    public void atualizarPessoaFisica(PessoaFisica pessoaFisica) {
        jdbcTemplate.update(
                "UPDATE PESSOA SET NME_PESSOA = ?" +
                        " WHERE COD_USUARIO = ?",
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
    public void deletarPessoaFisica(int idPessoaFisica) {
        jdbcTemplate.update(
                "DELETE FROM `PESSOA` WHERE `COD_PESSOA` = ? ",
                idPessoaFisica
        );
        jdbcTemplate.update(
                "DELETE FROM `PESSOA_FISICA` WHERE `COD_PESSOA` = ? ",
                idPessoaFisica
        );
    }

}

package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.PessoaJuridica;
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
import java.util.List;

@Repository
public class PessoaJuridicaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_PESSOA_JURIDICA =
         " SELECT P.COD_PESSOA, P.NME_PESSOA, PJ.NME_RAZAO_SOCIAL, PJ.COD_CNPJ " +
            " FROM PESSOA P INNER JOIN PESSOA_JURIDICA PJ ON P.COD_PESSOA = PJ.COD_PESSOA";
    private static final String COL_NME_PESSOA = "NME_PESSOA";
    private static final String COL_NME_RAZAO_SOCIAL = "NME_RAZAO_SOCIAL";
    private static final String COL_COD_CNPJ = "COD_CNPJ";
    private static final String COL_COD_PESSOA = "COD_PESSOA";

    @Transactional
    public void cadastrarPessoaJuridica(PessoaJuridica  pessoaJuridica) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String INSERT_PESSOA_JURIDICA = "INSERT INTO PESSOA (NME_PESSOA) VALUES (?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        INSERT_PESSOA_JURIDICA, new String[] { COL_COD_PESSOA }
                );
                ps.setString(1, pessoaJuridica.getNomePessoa());
                return ps;
            }
        }, keyHolder);

        jdbcTemplate.update(
                "INSERT INTO PESSOA_JURIDICA (COD_PESSOA, NME_RAZAO_SOCIAL, COD_CNPJ ) " +
                        " VALUES (?, ?, ?)",
                keyHolder.getKey().intValue(),//nosonar
                pessoaJuridica.getNomeRazaoSocial(),
                pessoaJuridica.getCodCNPJ()
        );
    }

    public PessoaJuridica buscarPessoaJuridicaPorId(int idPessoaJuridica) {
        return jdbcTemplate.queryForObject(
                SELECT_PESSOA_JURIDICA + " WHERE P.COD_PESSOA = ? "
                , (resultSet, rowNum) ->
                        new PessoaJuridica(
                                resultSet.getInt(COL_COD_PESSOA), // codPessoa
                                resultSet.getString(COL_NME_PESSOA),
                                resultSet.getString(COL_NME_RAZAO_SOCIAL),
                                resultSet.getString(COL_COD_CNPJ)
                        ),
                idPessoaJuridica
        );
    }

    public PessoaJuridica buscarPessoaJuridicaPorNome(String nomePessoaJuridica) {
            return jdbcTemplate.queryForObject(
                    SELECT_PESSOA_JURIDICA + " WHERE P.COD_PESSOA = ? "
                    , (resultSet, rowNum) ->
                    /*
                    Integer codPessoa, String nomePessoa, Integer codPessoaJuridica,
                          String nomeRazaoSocial, String codCNPJ
                    * */
                            new PessoaJuridica(
                                    resultSet.getInt(COL_COD_PESSOA), // codPessoa
                                    resultSet.getString(COL_NME_PESSOA),
                                    resultSet.getString(COL_NME_RAZAO_SOCIAL),
                                    resultSet.getString(COL_COD_CNPJ)
                            ),
                    "%" + nomePessoaJuridica + "%"
            );
    }
    public List<PessoaJuridica> listarPessoasJuridicas() {
        return jdbcTemplate.query(SELECT_PESSOA_JURIDICA
                , (resultSet, rowNum) ->
                        new PessoaJuridica(
                                resultSet.getInt(COL_COD_PESSOA), // codPessoa
                                resultSet.getString(COL_NME_PESSOA),
                                resultSet.getString(COL_NME_RAZAO_SOCIAL),
                                resultSet.getString(COL_COD_CNPJ)
                        )
        );
    }


    @Transactional
    public void atualizarPessoaJuridica(PessoaJuridica pessoaJuridica) {
        jdbcTemplate.update(
                "UPDATE PESSOA SET NME_PESSOA = ?" +
                        " WHERE COD_PESSOA = ?",
                pessoaJuridica.getNomePessoa(),
                pessoaJuridica.getCodPessoa()
        );
        jdbcTemplate.update(
                "UPDATE PESSOA_JURIDICA SET NME_RAZAO_SOCIAL = ?, COD_CNPJ = ? " +
                        " WHERE COD_PESSOA = ?",
                pessoaJuridica.getNomeRazaoSocial(),
                pessoaJuridica.getCodCNPJ(),
                pessoaJuridica.getCodPessoa()

        );
    }

    @Transactional
    public void deletarPessoaJuridica(int idPessoaJuridica) throws DataIntegrityViolationException {
        jdbcTemplate.update(
                "DELETE FROM PESSOA_JURIDICA WHERE COD_PESSOA = ? ",
                idPessoaJuridica
        );
        jdbcTemplate.update(
                "DELETE FROM PESSOA WHERE COD_PESSOA = ? ",
                idPessoaJuridica
        );
    }


}

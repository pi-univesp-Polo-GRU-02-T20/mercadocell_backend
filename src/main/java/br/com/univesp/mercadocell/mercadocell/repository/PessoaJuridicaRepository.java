package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.PessoaJuridica;
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
import java.util.List;

@Repository
public class PessoaJuridicaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_PESSOA_JURIDICA =
         " SELECT P.COD_PESSOA, P.NME_PESSOA, PJ.NME_RAZAO_SOCIAL, PJ.COD_CNPJ " +
            " FROM PESSOA P INNER JOIN PESSOA_JURIDICA PJ ON P.COD_PESSOA = PJ.COD_PESSOA";

    @Transactional
    public void cadastrarPessoaJuridica(PessoaJuridica  pessoaJuridica) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String INSERT_PESSOA_JURIDICA = "INSERT INTO PESSOA (NME_PESSOA) VALUES (?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        INSERT_PESSOA_JURIDICA, new String[] { "COD_PESSOA" }
                );
                ps.setString(1, pessoaJuridica.getNomePessoa());
                return ps;
            }
        }, keyHolder);
        int codPessoa  = keyHolder.getKey().intValue();
        jdbcTemplate.update(
                "INSERT INTO `PESSOA_JURIDICA` (COD_PESSOA, NME_RAZAO_SOCIAL, COD_CNPJ ) " +
                        " VALUES (?, ?, ?)",
                codPessoa,
                pessoaJuridica.getNomeRazaoSocial(),
                pessoaJuridica.getCodCNPJ()
        );
    }

    public PessoaJuridica buscarPessoaJuridicaPorId(int idPessoaJuridica) {
        try {
            return jdbcTemplate.queryForObject(
                    SELECT_PESSOA_JURIDICA + " WHERE P.COD_PESSOA = ? "
                    , (resultSet, rowNum) ->
                            new PessoaJuridica(
                                    resultSet.getInt("COD_PESSOA"), // codPessoa
                                    resultSet.getString("NME_PESSOA"),
                                    resultSet.getInt("COD_PESSOA"), // codPessoaJuridica
                                    resultSet.getString("NME_RAZAO_SOCIAL"),
                                    resultSet.getString("COD_CNPJ")
                            ),
                    new Object[]{idPessoaJuridica}
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public PessoaJuridica buscarPessoaJuridicaPorNome(String nomePessoaJuridica) {
        try {
            return jdbcTemplate.queryForObject(
                    SELECT_PESSOA_JURIDICA + " WHERE P.COD_PESSOA = ? "
                    , (resultSet, rowNum) ->
                    /*
                    Integer codPessoa, String nomePessoa, Integer codPessoaJuridica,
                          String nomeRazaoSocial, String codCNPJ
                    * */
                            new PessoaJuridica(
                                    resultSet.getInt("COD_PESSOA"), // codPessoa
                                    resultSet.getString("NME_PESSOA"),
                                    resultSet.getInt("COD_PESSOA"), // codPessoaJuridica
                                    resultSet.getString("NME_RAZAO_SOCIAL"),
                                    resultSet.getString("COD_CNPJ")
                            ),
                    new Object[]{"%" + nomePessoaJuridica + "%"}
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
    public List<PessoaJuridica> listarPessoasJuridicas() {
        return jdbcTemplate.query(SELECT_PESSOA_JURIDICA
                , (resultSet, rowNum) ->
                        new PessoaJuridica(
                                resultSet.getInt("COD_PESSOA"), // codPessoa
                                resultSet.getString("NME_PESSOA"),
                                resultSet.getInt("COD_PESSOA"), // codPessoaJuridica
                                resultSet.getString("NME_RAZAO_SOCIAL"),
                                resultSet.getString("COD_CNPJ")
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
    public void deletarPessoaJuridica(int idPessoaJuridica) {
        jdbcTemplate.update(
                "DELETE FROM `PESSOA_JURIDICA` WHERE `COD_PESSOA` = ? ",
                idPessoaJuridica
        );
        jdbcTemplate.update(
                "DELETE FROM `PESSOA` WHERE `COD_PESSOA` = ? ",
                idPessoaJuridica
        );
    }


}

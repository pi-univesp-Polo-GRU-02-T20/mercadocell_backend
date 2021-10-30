package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import br.com.univesp.mercadocell.mercadocell.model.Pessoa;
import br.com.univesp.mercadocell.mercadocell.model.PessoaFisica;
import br.com.univesp.mercadocell.mercadocell.model.TipoSexo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PessoaFisicaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    KeyHolder keyHolder = new GeneratedKeyHolder();
    private static final String queryPessoaFisica =
            " SELECT PF.COD_PESSOA_FISICA, U.NME_PESSOA, PF.SGL_UF_NATURALIDADE, PF.DTA_NASCIMENTO, PF.TPO_SEXO \n" +
            " FROM USUARIO U INNER JOIN PESSOA_FISICA PF WHERE U.COD_USUARIO = PF.COD_USUARIO";

    public void cadastrarPessoaFisica(PessoaFisica  pessoaFisica) {
        final String stmtCadPessoaFisica = "INSERT INTO USUARIO (NME_PESSOA, DSC_LOGIN, DSC_SENHA, FLG_ATIVO) " +
                " VALUES (?, ?, ?, ?);" +
                " select LAST_INSERT_ID(); ";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(stmtCadPessoaFisica,
                    Statement.RETURN_GENERATED_KEYS);
            return ps;
        }, keyHolder);

        int codUsuario  = keyHolder.getKey().intValue();

        jdbcTemplate.update(
                "INSERT INTO `PESSOA_FISICA` (COD_USUARIO, SGL_UF_NATURALIDADE, DTA_NASCIMENTO, TPO_SEXO ) " +
                        " VALUES (?, ?, ?, ?)",
                        codUsuario,
                        pessoaFisica.getEstadoNaturalidade(),
                        pessoaFisica.getDataNascimento(),
                        pessoaFisica.getTipoSexo().getSglTipoSexo()
        );

        /*
        * final String SQL = "INSERT INTO ... RETUNING id";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL,
                                   Statement.RETURN_GENERATED_KEYS);

            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue()
        *
        * */

        /**
         * COD_USUARIO	int	NO	PRI		auto_increment
         * NME_PESSOA	varchar(50)	YES
         * DSC_LOGIN	varchar(20)	YES	UNI
         * DSC_SENHA	varchar(128)	YES
         * FLG_ATIVO	tinyint	YES
         *
         */

        /*
            COD_PESSOA_FISICA	int	NO	PRI		auto_increment
            COD_PESSOA	int	NO	PRI
            SGL_UF_NATURALIDADE	char(2)	YES
            DTA_NASCIMENTO	date	NO
            TPO_SEXO	enum('M','F','N')	YES
        */
    }

    public PessoaFisica buscarPessoaFisicaPorId(int idPessoaFisica) {
        try {
        return jdbcTemplate.queryForObject(
                queryPessoaFisica + "WHERE COD_PESSOA_FISICA = ? "
                    , (rs, rowNum) ->
                            new PessoaFisica(
                                    rs.getInt("COD_PESSOA_FISICA"),
                                    rs.getString("NME_PESSOA"),
                                    rs.getString("SGL_UF_NATURALIDADE"),
                                    rs.getDate("DTA_NASCIMENTO"),
                                    TipoSexo.valueOf(rs.getString("TPO_SEXO"))
                            ),
                    new Object[]{idPessoaFisica}
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<PessoaFisica> listarPessoasFisicas() {
        return jdbcTemplate.query(queryPessoaFisica
                , (rs, rowNum) ->
                        new PessoaFisica(
                                rs.getInt("COD_PESSOA_FISICA"),
                                rs.getString("NME_PESSOA"),
                                rs.getString("SGL_UF_NATURALIDADE"),
                                rs.getDate("DTA_NASCIMENTO"),
                                TipoSexo.valueOf(rs.getString("TPO_SEXO"))
                        )
        );
    }

    public void atualizarCategoria(PessoaFisica pessoaFisica) {
        jdbcTemplate.update(
                "UPDATE PESSOA_FISICA SET SGL_UF_NATURALIDADE = ?, DTA_NASCIMENTO = ?, TPO_SEXO = ?" +
                        " WHERE COD_PESSOA_FISICA = ?",
                    pessoaFisica.getEstadoNaturalidade(),
                    pessoaFisica.getDataNascimento(),
                    pessoaFisica.getTipoSexo().getSglTipoSexo(),
                    pessoaFisica.getCodPessoaFisica()
        );
        jdbcTemplate.update(
                "UPDATE USUARIO SET NME_PESSOA = ?" +
                        " WHERE COD_USUARIO = ?",
                pessoaFisica.getNomePessoa(),
                pessoaFisica.getCodUsuario()
        );
    }

    public void deletarCategoria(int idPessoaFisica) {
        jdbcTemplate.update(
                "DELETE FROM `PESSOA_FISICA` WHERE `COD_PESSOA_FISICA` = ?",
                idPessoaFisica
        );
    }

}

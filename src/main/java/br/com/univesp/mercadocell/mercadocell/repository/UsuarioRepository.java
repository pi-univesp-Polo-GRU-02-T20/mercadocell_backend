package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public static final String MASCARA_SENHA = "*******";
    private static final String SELECT_USUARIO =
            "SELECT U.COD_USUARIO, U.DSC_LOGIN, U.FLG_ATIVO, P.COD_PESSOA, P.NME_PESSOA,U.DSC_COMPLEMENTO_SENHA " +
            "FROM USUARIO U LEFT JOIN PESSOA P ON U.COD_PESSOA  = P.COD_PESSOA";
    private static final String COLUNA_COD_USUARIO = "COD_USUARIO";
    private static final String COLUNA_DSC_LOGIN = "DSC_LOGIN";
    private static final String COLUNA_FLG_ATIVO = "FLG_ATIVO";
    private static final String COLUNA_COD_PESSOA = "COD_PESSOA";
    private static final String COLUNA_NME_PESSOA = "NME_PESSOA";
    private static final String COLUNA_DSC_SENHA = "DSC_SENHA";
    private static final String COLUNA_DSC_COMPLEMENTO_SENHA = "DSC_COMPLEMENTO_SENHA";

    public void cadastrarUsuario(Usuario usuario)
            throws DataIntegrityViolationException {
            jdbcTemplate.update(
                    "INSERT INTO USUARIO ( COD_USUARIO, DSC_LOGIN, DSC_SENHA, FLG_ATIVO, COD_PESSOA," +
                            " DSC_COMPLEMENTO_SENHA) VALUES (?, ?, ?, ?, ?, ?)",
                    usuario.getCodUsuario(),
                    usuario.getLogin(),
                    usuario.getSenha(),
                    usuario.getAtivo(),
                    usuario.getCodPessoa(),
                    usuario.getComplementoSenha()
            );
    }

    public Usuario buscarUsuarioPorId(int idUsuario) {
            return jdbcTemplate.queryForObject(
                    SELECT_USUARIO + " WHERE COD_USUARIO = ?;"
                    , (rs, rowNum) ->
                            new Usuario(
                                            rs.getInt(COLUNA_COD_PESSOA),
                                            rs.getString(COLUNA_NME_PESSOA),
                                            rs.getString(COLUNA_DSC_LOGIN),
                                            rs.getString(COLUNA_DSC_SENHA),
                                            rs.getBoolean(COLUNA_FLG_ATIVO),
                                            rs.getInt(COLUNA_COD_PESSOA)
                                ),
                    idUsuario
            );
    }
    public Usuario buscarUsuarioPorLogin(String loginUsuario) {
            return jdbcTemplate.queryForObject(
                    "SELECT COD_USUARIO, DSC_LOGIN, DSC_SENHA, FLG_ATIVO, COD_PESSOA, DSC_COMPLEMENTO_SENHA " +
                            " FROM USUARIO WHERE DSC_LOGIN = ?"
                    , (resultSet, rowNum) ->
                            new Usuario(
                                    resultSet.getInt(COLUNA_COD_USUARIO),
                                    resultSet.getString(COLUNA_DSC_LOGIN),
                                    resultSet.getString(COLUNA_DSC_SENHA),
                                    resultSet.getBoolean(COLUNA_FLG_ATIVO),
                                    resultSet.getInt(COLUNA_COD_PESSOA),
                                    resultSet.getString(COLUNA_DSC_COMPLEMENTO_SENHA)
                            ),
                    loginUsuario
            );
    }

    public List<Usuario> listarUsuarios() {
        return jdbcTemplate.query(SELECT_USUARIO
                , (resultSet, rowNum) ->
                        new Usuario(
                                resultSet.getInt(COLUNA_COD_USUARIO),
                                resultSet.getString(COLUNA_NME_PESSOA),
                                resultSet.getString(COLUNA_DSC_LOGIN),
                                resultSet.getString(COLUNA_DSC_SENHA),
                                resultSet.getBoolean(COLUNA_FLG_ATIVO),
                                resultSet.getInt(COLUNA_COD_PESSOA)
                        )
        );
    }

    public void atualizarUsuario(Usuario usuario)throws DataIntegrityViolationException {
        jdbcTemplate.update(
                "UPDATE USUARIO SET DSC_LOGIN = ?, DSC_SENHA = ?, FLG_ATIVO = ?, COD_PESSOA = ? " +
                        " WHERE  COD_USUARIO = ?",
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getAtivo(),
                usuario.getCodPessoa(),
                usuario.getCodUsuario()
        );
    }

    public void deletarUsuario(int idUsuario) {
        jdbcTemplate.update(
                "DELETE FROM USUARIO WHERE COD_USUARIO = ?",
                idUsuario
        );
    }
}

package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Pessoa;
import br.com.univesp.mercadocell.mercadocell.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Repository
public class UsuarioRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public static String mascaraSenha = "*******";
    private String SELECT_USUARIO = "SELECT U.COD_USUARIO, U.DSC_LOGIN, U.FLG_ATIVO, P.COD_PESSOA, P.NME_PESSOA " +
            "FROM `USUARIO` U LEFT JOIN PESSOA P ON U.COD_PESSOA  = P.COD_PESSOA";

    public void cadastrarUsuario(Usuario usuario)
            throws DataIntegrityViolationException {
            jdbcTemplate.update(
                    "INSERT INTO USUARIO ( COD_USUARIO, DSC_LOGIN, DSC_SENHA, FLG_ATIVO, COD_PESSOA) VALUES (?, ?, ?, ?, ?)",
                    usuario.getCodUsuario(),
                    usuario.getLogin(),
                    usuario.getSenha(),
                    usuario.getAtivo(),
                    usuario.getCodPessoa()
            );
    }

    public Usuario buscarUsuarioPorId(int idUsuario) {
            return jdbcTemplate.queryForObject(
                    SELECT_USUARIO + " WHERE `COD_USUARIO` = ?;"
                    , (rs, rowNum) ->
                            new Usuario(
                                            rs.getInt("COD_PESSOA"),
                                            rs.getString("NME_PESSOA"),
                                            rs.getString("DSC_LOGIN"),
                                            mascaraSenha,
                                            rs.getBoolean("FLG_ATIVO"),
                                            rs.getInt("COD_PESSOA")
                                ),
                    new Object[]{idUsuario}
            );
    }
    public Usuario buscarUsuarioPorLogin(String loginUsuario) {
            return jdbcTemplate.queryForObject("SELECT COD_USUARIO, DSC_LOGIN, DSC_SENHA, FLG_ATIVO, COD_PESSOA" +
                            " FROM `USUARIO` WHERE `DSC_LOGIN` = ?"
                    , (resultSet, rowNum) ->
                            new Usuario(
                                    resultSet.getInt("COD_USUARIO"),
                                    resultSet.getString("DSC_LOGIN"),
                                    resultSet.getString("DSC_SENHA"),
                                    resultSet.getBoolean("FLG_ATIVO"),
                                    resultSet.getInt("COD_PESSOA")
                            ),
                    new Object[]{loginUsuario}
            );
    }

    public List<Usuario> listarUsuarios() {
        return jdbcTemplate.query(SELECT_USUARIO
                , (resultSet, rowNum) ->
                        new Usuario(
                                resultSet.getInt("COD_USUARIO"),
                                resultSet.getString("NME_PESSOA"),
                                resultSet.getString("DSC_LOGIN"),
                                mascaraSenha,
                                resultSet.getBoolean("FLG_ATIVO"),
                                resultSet.getInt("COD_PESSOA")
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
                "DELETE FROM `USUARIO` WHERE `COD_USUARIO` = ?",
                idUsuario
        );
    }
}

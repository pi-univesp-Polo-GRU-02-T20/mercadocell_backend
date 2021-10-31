package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Pessoa;
import br.com.univesp.mercadocell.mercadocell.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public static String mascaraSenha = "*******";
    private String SELECT_USUARIO = "SELECT U.COD_USUARIO, U.DSC_LOGIN, U.FLG_ATIVO, P.COD_PESSOA, P.NME_PESSOA " +
            "FROM `USUARIO` U LEFT JOIN PESSOA P ON U.COD_PESSOA  = P.COD_PESSOA";

    public void cadastrarUsuario(Usuario usuario) {
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
        try {
            return jdbcTemplate.queryForObject(
                    SELECT_USUARIO + " WHERE `COD_USUARIO` = ?;"
                    , (rs, rowNum) ->
                            new Usuario(
                                            rs.getInt("COD_PESSOA"),
                                            rs.getString("NME_PESSOA"),
                                            rs.getInt("COD_USUARIO"),
                                            rs.getString("DSC_LOGIN"),
                                            mascaraSenha,
                                            rs.getBoolean("FLG_ATIVO")
                                ),
                    new Object[]{idUsuario}
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    public Usuario buscarUsuarioPorLogin(String loginUsuario) {
        try {
            return jdbcTemplate.queryForObject("SELECT COD_USUARIO, DSC_LOGIN, DSC_SENHA" +
                            " FROM `USUARIO` WHERE `DSC_LOGIN` = ?"
                    , (rs, rowNum) ->
                            new Usuario(
                                    rs.getInt("COD_USUARIO"),
                                    rs.getString("DSC_LOGIN"),
                                    rs.getString("DSC_SENHA"),
                                    null
                            ),
                    new Object[]{loginUsuario}
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Usuario> listarUsuarios() {
        return jdbcTemplate.query(SELECT_USUARIO
                , (rs, rowNum) ->
                        new Usuario(
                                rs.getInt("COD_PESSOA"),
                                rs.getString("NME_PESSOA"),
                                rs.getInt("COD_USUARIO"),
                                rs.getString("DSC_LOGIN"),
                                mascaraSenha,
                                rs.getBoolean("FLG_ATIVO")
                        )
        );
    }

    public void atualizarUsuario(Usuario usuario) {
        jdbcTemplate.update(
                "UPDATE `USUARIO` SET DSC_LOGIN = ?, DSC_SENHA = ?, FLG_ATIVO = ?, COD_PESSOA = ?" +
                        " WHERE `USUARIO`.`COD_USUARIO` = ?",
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

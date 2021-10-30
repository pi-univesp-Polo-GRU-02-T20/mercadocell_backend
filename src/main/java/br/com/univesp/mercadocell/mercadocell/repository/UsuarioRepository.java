package br.com.univesp.mercadocell.mercadocell.repository;

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

    public void cadastrarUsuario(Usuario usuario) {
        jdbcTemplate.update(
                "INSERT INTO USUARIO (NME_PESSOA, DSC_LOGIN, DSC_SENHA, FLG_ATIVO) VALUES (?, ?, ?, ?)",
                usuario.getNomePessoa(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getAtivo()
        );
    }

    public Usuario buscarUsuarioPorId(int idUsuario) {
        try {
            return jdbcTemplate.queryForObject("SELECT COD_USUARIO, NME_PESSOA, DSC_LOGIN, FLG_ATIVO " +
                            " FROM `USUARIO` WHERE `COD_USUARIO` = ?"
                    , (rs, rowNum) ->
                            new Usuario(
                                    rs.getInt("COD_USUARIO"),
                                    rs.getString("NME_PESSOA"),
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
            return jdbcTemplate.queryForObject("SELECT DSC_LOGIN, DSC_SENHA FROM `USUARIO` WHERE `DSC_LOGIN` = ?"
                    , (rs, rowNum) ->
                            new Usuario(
                                    rs.getString("DSC_LOGIN"),
                                    rs.getString("DSC_SENHA")
                            ),
                    new Object[]{loginUsuario}
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Usuario> listarUsuarios() {
        return jdbcTemplate.query("SELECT COD_USUARIO, NME_PESSOA, DSC_LOGIN, FLG_ATIVO FROM `USUARIO`"
                , (rs, rowNum) ->
                        new Usuario(
                                rs.getInt("COD_USUARIO"),
                                rs.getString("NME_PESSOA"),
                                rs.getString("DSC_LOGIN"),
                                mascaraSenha,
                                rs.getBoolean("FLG_ATIVO")
                        )
        );
    }

    public void atualizarUsuario(Usuario usuario) {
        jdbcTemplate.update(
                "UPDATE `USUARIO` SET `NME_PESSOA` = ?, DSC_LOGIN = ?, DSC_SENHA = ?, FLG_ATIVO = ? " +
                        " WHERE `USUARIO`.`COD_USUARIO` = ?",
                usuario.getNomePessoa(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getAtivo(),
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

package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Repository
public class CategoriaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void cadastrarCategoria(Categoria categoria) {
        jdbcTemplate.update(
                "INSERT INTO `CATEGORIA` (`NME_CATEGORIA`) VALUES (?)",
                categoria.getNomeCategoria()
        );
    }

    public Categoria buscarCategoriaPorId(int idCategoria) {
            return jdbcTemplate.queryForObject("SELECT COD_CATEGORIA FROM `CATEGORIA` WHERE `COD_CATEGORIA` = ?"
                    , (rs, rowNum) ->
                            new Categoria(
                                    rs.getInt("COD_CATEGORIA"),
                                    rs.getString("NME_CATEGORIA")
                            ),
                    new Object[]{idCategoria}
            );
    }

    public Categoria buscarCategoriaPorNome(String nomeCategoria) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT COD_CATEGORIA, NME_CATEGORIA FROM CATEGORIA WHERE NME_CATEGORIA = ?"
                    , (rs, rowNum) ->
                            new Categoria(
                                    rs.getInt("COD_CATEGORIA"),
                                    rs.getString("NME_CATEGORIA")
                            ),
                    new Object[]{nomeCategoria}
            );
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Categoria> listarCategorias() {
        try{
            return jdbcTemplate.query("SELECT COD_CATEGORIA, NME_CATEGORIA FROM `CATEGORIA`"
                    , (rs, rowNum) ->
                            new Categoria(
                                    rs.getInt("COD_CATEGORIA"),
                                    rs.getString("NME_CATEGORIA")
                            )
            );
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Nenhuma categoria encontrada");
        }
    }

    public void atualizarCategoria(Categoria categoria) {
        jdbcTemplate.update(
                "UPDATE `CATEGORIA` SET `NME_CATEGORIA` = ? WHERE `CATEGORIA`.`COD_CATEGORIA` = ?",
                categoria.getNomeCategoria(),
                categoria.getCodCategoria()
        );
    }

    public void deletarCategoria(int idCategoria) throws DataIntegrityViolationException /*, SQLIntegrityConstraintViolationException*/ {
        jdbcTemplate.update(
                "DELETE FROM `CATEGORIA` WHERE `CATEGORIA`.`COD_CATEGORIA` = ?",
                idCategoria
        );
    }
}

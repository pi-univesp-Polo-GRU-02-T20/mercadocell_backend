package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM `CATEGORIA` WHERE `COD_CATEGORIA` = ?"
                    , (rs, rowNum) ->
                            new Categoria(
                                    rs.getInt("COD_CATEGORIA"),
                                    rs.getString("NME_CATEGORIA")
                            ),
                    new Object[]{idCategoria}
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Categoria> listarCategorias() {
        return jdbcTemplate.query("SELECT * FROM `CATEGORIA`"
                , (rs, rowNum) ->
                        new Categoria(
                                rs.getInt("COD_CATEGORIA"),
                                rs.getString("NME_CATEGORIA")
                        )
        );
    }

    public void atualizarCategoria(Categoria categoria) {
        jdbcTemplate.update(
                "UPDATE `CATEGORIA` SET `NME_CATEGORIA` = ? WHERE `CATEGORIA`.`COD_CATEGORIA` = ?",
                categoria.getNomeCategoria(),
                categoria.getCodCategoria()
        );
    }

    public void deletarCategoria(int idCategoria) {
        jdbcTemplate.update(
                "DELETE FROM `CATEGORIA` WHERE `CATEGORIA`.`COD_CATEGORIA` = ?",
                idCategoria
        );
    }

}

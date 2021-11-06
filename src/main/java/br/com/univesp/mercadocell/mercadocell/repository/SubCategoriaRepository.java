package br.com.univesp.mercadocell.mercadocell.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import br.com.univesp.mercadocell.mercadocell.model.SubCategoria;

@Repository
public class SubCategoriaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String SELECT_SUBCATEGORIA= "SELECT CT.COD_CATEGORIA,CT.NME_CATEGORIA,SC.COD_SUBCATEGORIA, SC.NME_SUBCATEGORIA " +
                                    " FROM CATEGORIA CT JOIN SUBCATEGORIA SC " +
                                    " ON SC.COD_CATEGORIA = CT.COD_CATEGORIA ";

    public void cadastrarSubCategoria(SubCategoria subcategoria, CategoriaRepository categoriaRepository) {
           jdbcTemplate.update("INSERT INTO SUBCATEGORIA (NME_SUBCATEGORIA, COD_CATEGORIA ) VALUES (?, ?)",
                    subcategoria.getNomeSubCategoria(), subcategoria.getCategoria().getCodCategoria()
            );
    }

    public SubCategoria buscarSubCategoriaPorId(int idSubCategoria) {
            return jdbcTemplate.queryForObject( SELECT_SUBCATEGORIA+ " WHERE `COD_SUBCATEGORIA` = ?"
                    , (rs, rowNum) ->
                            new SubCategoria(
                            rs.getInt("COD_SUBCATEGORIA"),
                            rs.getString("NME_SUBCATEGORIA"),
                            new Categoria(
                                    rs.getInt("COD_CATEGORIA"),
                                    rs.getString("NME_CATEGORIA")
                            )
                    ),
                    new Object[]{idSubCategoria}
            );
    }

    public List<SubCategoria> listarSubCategorias() {
        return jdbcTemplate.query(  SELECT_SUBCATEGORIA
                , (rs, rowNum) ->
                        new SubCategoria(
                                rs.getInt("COD_SUBCATEGORIA"),
                                rs.getString("NME_SUBCATEGORIA"),
                                new Categoria(
                                            rs.getInt("COD_CATEGORIA"),
                                            rs.getString("NME_CATEGORIA")
                                )
                        )
        );
    }


    public void atualizarSubCategoria(SubCategoria subCategoria) {
        jdbcTemplate.update(
                "UPDATE SUBCATEGORIA SET NME_SUBCATEGORIA = ? , COD_CATEGORIA = ? WHERE COD_SUBCATEGORIA = ?",
                subCategoria.getNomeSubCategoria(),
                subCategoria.getCategoria().getCodCategoria(),
                subCategoria.getCodSubCategoria()
            );
    }

    public void deletarSubCategoria(int idSubCategoria) {
        jdbcTemplate.update("DELETE FROM SUBCATEGORIA WHERE COD_SUBCATEGORIA = ?",
                idSubCategoria
        );
    }

}

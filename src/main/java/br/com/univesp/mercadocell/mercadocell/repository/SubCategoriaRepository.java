package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import br.com.univesp.mercadocell.mercadocell.model.SubCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubCategoriaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String
            SELECT_SUBCATEGORIA= "SELECT CT.COD_CATEGORIA,CT.NME_CATEGORIA,SC.COD_SUBCATEGORIA, SC.NME_SUBCATEGORIA " +
                                    " FROM CATEGORIA CT JOIN SUBCATEGORIA SC " +
                                    " ON SC.COD_CATEGORIA = CT.COD_CATEGORIA ";
    private static final String COL_COD_CATEGORIA = "COD_CATEGORIA";
    private static final String COL_NME_CATEGORIA = "NME_CATEGORIA";
    private static final String COL_COD_SUBCATEGORIA = "COD_SUBCATEGORIA";
    private static final String COL_NME_SUBCATEGORIA = "NME_SUBCATEGORIA";

    public void cadastrarSubCategoria(SubCategoria subcategoria) {
           jdbcTemplate.update("INSERT INTO SUBCATEGORIA (NME_SUBCATEGORIA, COD_CATEGORIA ) VALUES (?, ?)",
                    subcategoria.getNomeSubCategoria(), subcategoria.getCategoria().getCodCategoria()
            );
    }

    public SubCategoria buscarSubCategoriaPorId(int idSubCategoria) {
            return jdbcTemplate.queryForObject( SELECT_SUBCATEGORIA+ " WHERE `COD_SUBCATEGORIA` = ?"
                    , (rs, rowNum) ->
                            new SubCategoria(
                            rs.getInt(COL_COD_SUBCATEGORIA),
                            rs.getString(COL_NME_SUBCATEGORIA),
                            new Categoria(
                                    rs.getInt(COL_COD_CATEGORIA),
                                    rs.getString(COL_NME_CATEGORIA)
                            )
                    ),
                    idSubCategoria
            );
    }

    public List<SubCategoria> listarSubCategorias() {
        return jdbcTemplate.query(  SELECT_SUBCATEGORIA
                , (rs, rowNum) ->
                        new SubCategoria(
                                rs.getInt(COL_COD_SUBCATEGORIA),
                                rs.getString(COL_NME_SUBCATEGORIA),
                                new Categoria(
                                            rs.getInt(COL_COD_CATEGORIA),
                                            rs.getString(COL_NME_CATEGORIA)
                                )
                        )
        );
    }


    public void atualizarSubCategoria(SubCategoria subCategoria) throws DataIntegrityViolationException {
            jdbcTemplate.update(
                    "UPDATE SUBCATEGORIA SET NME_SUBCATEGORIA = ? , COD_CATEGORIA = ? WHERE COD_SUBCATEGORIA = ?",
                    subCategoria.getNomeSubCategoria(),
                    subCategoria.getCategoria().getCodCategoria(),
                    subCategoria.getCodSubCategoria()
            );
    }

    public void deletarSubCategoria(int idSubCategoria) throws DataIntegrityViolationException {
        jdbcTemplate.update("DELETE FROM SUBCATEGORIA WHERE COD_SUBCATEGORIA = ?",
                idSubCategoria
        );
    }

    public SubCategoria buscarSubCategoriaPorNome(String nomeCategoria){
            return jdbcTemplate.queryForObject(
                    "SELECT COD_SUBCATEGORIA, NME_SUBCATEGORIA " +
                            " FROM SUBCATEGORIA WHERE NME_SUBCATEGORIA = ?"
                    , (resultSet, rowNum) ->
                            new SubCategoria(
                                    resultSet.getInt(COL_COD_SUBCATEGORIA),
                                    resultSet.getString(COL_NME_SUBCATEGORIA)
                            ),
                    nomeCategoria
            );
    }
}

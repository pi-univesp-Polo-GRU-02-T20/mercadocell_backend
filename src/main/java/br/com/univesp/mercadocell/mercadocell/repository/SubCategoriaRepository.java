package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import br.com.univesp.mercadocell.mercadocell.model.SubCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubCategoriaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    ////todo ADICIONAR TRATAMENTO DE EXCEÇÕES DE BANCO
    public void cadastrarSubCategoria(SubCategoria subcategoria) {
           jdbcTemplate.update("CALL SP_CADASTRAR_SUBCATEGORIA(?, ?)",
                    subcategoria.getNomeSubCategoria(), subcategoria.getCategoria().getCodCategoria()
            );
    }
    public SubCategoria buscarSubCategoriaPorId(int idSubCategoria) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM `VW_SUBCATEGORIA` WHERE `COD_SUBCATEGORIA` = ?"
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
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<SubCategoria> listarSubCategorias() {
        return jdbcTemplate.query("SELECT * FROM `VW_SUBCATEGORIA`"
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
                "CALL SP_ALTERAR_SUBCATEGORIA (?, ?,?)",
                subCategoria.getCodSubCategoria(),
                subCategoria.getNomeSubCategoria(),
                subCategoria.getCategoria().getCodCategoria()
            );
    }

    public void deletarSubCategoria(int idSubCategoria) {
        jdbcTemplate.update(
                "CAll SP_EXCLUIR_SUBCATEGORIA(?)",
                idSubCategoria
        );
    }

}

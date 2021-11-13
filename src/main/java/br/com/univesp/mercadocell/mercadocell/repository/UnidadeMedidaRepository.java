package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.UnidadeMedida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UnidadeMedidaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void cadastrarUnidadeMedida(UnidadeMedida unidadeMedida){
        jdbcTemplate.update("INSERT INTO UNIDADE_MEDIDA (NME_UNIDADE_MEDIDA, SGL_UNIDADE_MEDIDA ) VALUES (?, ?)",
                unidadeMedida.getNomeUnidadeMedida(),
                unidadeMedida.getSiglaUnidadeMedida()
        );
    }

    public UnidadeMedida buscarUnidadeMedidaPorId(int idUnidadeMedida){
            return jdbcTemplate.queryForObject("SELECT COD_UNIDADE_MEDIDA, NME_UNIDADE_MEDIDA, SGL_UNIDADE_MEDIDA " +
                            "FROM `UNIDADE_MEDIDA` WHERE `COD_UNIDADE_MEDIDA` = ?"
                    , (rs, rowNum) ->
                            new UnidadeMedida(
                                    rs.getInt("COD_UNIDADE_MEDIDA"),
                                    rs.getString("NME_UNIDADE_MEDIDA"),
                                    rs.getString("SGL_UNIDADE_MEDIDA")
                            ),
                    new Object[]{idUnidadeMedida}
            );
    }

    public UnidadeMedida buscarUnidadeMedidaPorNome(String nomeUnidadeMedida){
        return jdbcTemplate.queryForObject("SELECT COD_UNIDADE_MEDIDA, NME_UNIDADE_MEDIDA, SGL_UNIDADE_MEDIDA " +
                        "FROM UNIDADE_MEDIDA WHERE NME_UNIDADE_MEDIDA = ?"
                , (rs, rowNum) ->
                        new UnidadeMedida(
                                rs.getInt("COD_UNIDADE_MEDIDA"),
                                rs.getString("NME_UNIDADE_MEDIDA"),
                                rs.getString("SGL_UNIDADE_MEDIDA")
                        ),
                new Object[]{nomeUnidadeMedida}
        );
    }

    public List<UnidadeMedida> listarUnidadeMedida(){
        return jdbcTemplate.query("SELECT COD_UNIDADE_MEDIDA, NME_UNIDADE_MEDIDA, SGL_UNIDADE_MEDIDA " +
                        "FROM `UNIDADE_MEDIDA`"
                , (rs, rowNum) ->
                        new UnidadeMedida(
                                rs.getInt("COD_UNIDADE_MEDIDA"),
                                rs.getString("NME_UNIDADE_MEDIDA"),
                                rs.getString("SGL_UNIDADE_MEDIDA")
                        )
        );
    }

    public void atualizarUnidadeMedida(UnidadeMedida unidadeMedida){
        jdbcTemplate.update(
                "UPDATE `UNIDADE_MEDIDA` UN" +
                        " SET `NME_UNIDADE_MEDIDA` = ?, SGL_UNIDADE_MEDIDA = ?" +
                        " WHERE `COD_UNIDADE_MEDIDA` = ?",
                unidadeMedida.getNomeUnidadeMedida(),
                unidadeMedida.getSiglaUnidadeMedida(),
                unidadeMedida.getCodUnidadeMedida()
        );
    }

    public void deletarUnidadeMedida(int idUnidadeMedida){
        jdbcTemplate.update(
                "DELETE FROM `UNIDADE_MEDIDA` UM WHERE `UM`.`COD_UNIDADE_MEDIDA` = ?",
                idUnidadeMedida
        );
    }
}

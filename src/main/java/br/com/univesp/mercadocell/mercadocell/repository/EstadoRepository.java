package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstadoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String COLUNA_COD_ESTADO = "COD_ESTADO";
    private static final String COLUNA_NME_ESTADO = "NME_ESTADO";
    private static final String COLUNA_SGL_UF = "SGL_UF";

    public void cadastrarEstado(Estado estado) {
       jdbcTemplate.update("INSERT INTO ESTADO ( COD_ESTADO , NME_ESTADO, SGL_UF ) " +
                       "VALUES (?, ?, ?)",
                        estado.getCodEstado(),
                        estado.getNomeEstado(),
                        estado.getSiglaUF()
        );
    }

    public Estado buscarEstadoPorId(int idEstado) {
        return jdbcTemplate.queryForObject(
                "SELECT COD_ESTADO, NME_ESTADO, SGL_UF  FROM " +
                        "ESTADO WHERE COD_ESTADO = ?"
                , (rs, rowNum) ->
                        new Estado(
                                rs.getInt(COLUNA_COD_ESTADO),
                                rs.getString(COLUNA_NME_ESTADO),
                                rs.getString(COLUNA_SGL_UF)
                        ),
               idEstado
        );
    }

    public List<Estado> listarEstados() {
        return jdbcTemplate.query(
                "SELECT COD_ESTADO,NME_ESTADO, SGL_UF FROM ESTADO"
                , (rs, rowNum) ->
                        new Estado(
                                rs.getInt(COLUNA_COD_ESTADO),
                                rs.getString(COLUNA_NME_ESTADO),
                                rs.getString(COLUNA_SGL_UF)
                        )
        );
    }

    public void atualizarEstado(Estado estado) {
        jdbcTemplate.update(
                "UPDATE ESTADO SET NME_ESTADO = ?, SGL_UF = ? WHERE COD_ESTADO = ?",
                estado.getNomeEstado(),
                estado.getSiglaUF(),
                estado.getCodEstado()
        );
    }

    public void deletarEstado(int idEstado) {
        jdbcTemplate.update(
                "DELETE FROM ESTADO WHERE COD_ESTADO = ?",
                idEstado
        );
    }

    public Estado buscarEstadoPorUF(String siglaUF) {
        return jdbcTemplate.queryForObject(
                "SELECT COD_ESTADO, NME_ESTADO, SGL_UF FROM " +
                        "ESTADO WHERE SGL_UF = ?"
                , (rs, rowNum) ->
                        new Estado(
                                rs.getInt(COLUNA_COD_ESTADO),
                                rs.getString(COLUNA_NME_ESTADO),
                                rs.getString(COLUNA_SGL_UF)
                        ),
                siglaUF
        );
    }
}

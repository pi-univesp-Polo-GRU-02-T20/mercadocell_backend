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
    private static final String CONSULTA_UNIDADE_MEDIDA =
            "SELECT COD_UNIDADE_MEDIDA, NME_UNIDADE_MEDIDA, SGL_UNIDADE_MEDIDA FROM UNIDADE_MEDIDA ";
    private static final String DELETE_UNIDADE_MEDIDA = " DELETE FROM UNIDADE_MEDIDA ";
    private static final String UPDATE_UNIDADE_MEDIDA =
                " UPDATE UNIDADE_MEDIDA  SET NME_UNIDADE_MEDIDA = ?, SGL_UNIDADE_MEDIDA = ?";
    private static final String COLUNA_COD_UNIDADE_MEDIDA = "COD_UNIDADE_MEDIDA";
    private static final String COLUNA_NME_UNIDADE_MEDIDA = "NME_UNIDADE_MEDIDA";
    private static final String COLUNA_SGL_UNIDADE_MEDIDA = "SGL_UNIDADE_MEDIDA";

    private static final String FILTRO_COD_UNIDADE_MEDIDA = " WHERE COD_UNIDADE_MEDIDA = ?";
    private static final String FILTRO_NME_UNIDADE_MEDIDA = " WHERE NME_UNIDADE_MEDIDA = ?";

    public void cadastrarUnidadeMedida(UnidadeMedida unidadeMedida){
        jdbcTemplate.update("INSERT INTO UNIDADE_MEDIDA (NME_UNIDADE_MEDIDA, SGL_UNIDADE_MEDIDA ) VALUES (?, ?)",
                unidadeMedida.getNomeUnidadeMedida(),
                unidadeMedida.getSiglaUnidadeMedida()
        );
    }

    public UnidadeMedida buscarUnidadeMedidaPorId(int idUnidadeMedida){
            return jdbcTemplate.queryForObject( CONSULTA_UNIDADE_MEDIDA + FILTRO_COD_UNIDADE_MEDIDA
                    , (rs, rowNum) ->
                            new UnidadeMedida(
                                    rs.getInt(COLUNA_COD_UNIDADE_MEDIDA),
                                    rs.getString(COLUNA_NME_UNIDADE_MEDIDA),
                                    rs.getString(COLUNA_SGL_UNIDADE_MEDIDA)
                            ),
                    idUnidadeMedida
            );
    }

    public UnidadeMedida buscarUnidadeMedidaPorNome(String nomeUnidadeMedida){
        return jdbcTemplate.queryForObject(CONSULTA_UNIDADE_MEDIDA + FILTRO_NME_UNIDADE_MEDIDA
                , (rs, rowNum) ->
                        new UnidadeMedida(
                                rs.getInt(COLUNA_COD_UNIDADE_MEDIDA),
                                rs.getString(COLUNA_NME_UNIDADE_MEDIDA),
                                rs.getString(COLUNA_SGL_UNIDADE_MEDIDA)
                        ),
                nomeUnidadeMedida
        );
    }

    public List<UnidadeMedida> listarUnidadeMedida(){
        return jdbcTemplate.query(CONSULTA_UNIDADE_MEDIDA
                , (rs, rowNum) ->
                        new UnidadeMedida(
                                rs.getInt(COLUNA_COD_UNIDADE_MEDIDA),
                                rs.getString(COLUNA_NME_UNIDADE_MEDIDA),
                                rs.getString(COLUNA_SGL_UNIDADE_MEDIDA)
                        )
        );
    }

    public void atualizarUnidadeMedida(UnidadeMedida unidadeMedida){
        jdbcTemplate.update(
                UPDATE_UNIDADE_MEDIDA + FILTRO_COD_UNIDADE_MEDIDA,
                unidadeMedida.getNomeUnidadeMedida(),
                unidadeMedida.getSiglaUnidadeMedida(),
                unidadeMedida.getCodUnidadeMedida()
        );
    }

    public void deletarUnidadeMedida(int idUnidadeMedida){
        jdbcTemplate.update(
                DELETE_UNIDADE_MEDIDA + FILTRO_COD_UNIDADE_MEDIDA,
                idUnidadeMedida
        );
    }
}

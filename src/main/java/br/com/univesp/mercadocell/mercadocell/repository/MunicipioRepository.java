package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Estado;
import br.com.univesp.mercadocell.mercadocell.model.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MunicipioRepository {

    static final String CONSULTA_MUNICIPIO = "SELECT COD_MUNICIPIO,NME_MUNICIPIO, COD_ESTADO " +
            " FROM MUNICIPIO M INNER JOIN ESTADO E " +
            " ON M.COD_ESTADO = E.COD_ESTADO " ;
    static final String FILTRO_MUNICIPIO_CODIGO = " WHERE COD_MUNICIPIO = ?";
    static final String FILTRO_MUNICIPIO_NOME = " WHERE NME_MUNICIPIO = ?";
    static final String UPDATE_MUNICIPIO = "UPDATE MUNICIPIO SET NME_MUNICIPIO = ?, COD_ESTADO = ?";

    static final String COLUNA_COD_MUNICIPIO = "COD_MUNICIPIO";
    static final String COLUNA_NME_MUNICIPIO = "NME_MUNICIPIO";


    @Autowired
    JdbcTemplate jdbcTemplate;
    public void cadastrarMunicipio(Municipio municipio) throws DataIntegrityViolationException {
        jdbcTemplate.update("INSERT INTO MUNICIPIO (COD_MUNICIPIO, NME_MUNICIPIO, COD_ESTADO) " +
                        "VALUES (?, ?, ?)",
                municipio.getCodMunicipio(),
                municipio.getNomeMunicipio(),
                municipio.getEstado().getCodEstado()
        );
    }

    public Municipio buscarMunicipioPorId(int idMunicipio) {
            return jdbcTemplate.queryForObject(
                    CONSULTA_MUNICIPIO + FILTRO_MUNICIPIO_CODIGO
                    , (rs, rowNum) ->
                            new Municipio(
                                    rs.getInt("COD_MUNICIPIO"),
                                    rs.getString("NME_MUNICIPIO"),
                                   new Estado(
                                            rs.getInt("COD_ESTADO"),
                                           rs.getString("NME_ESTADO"),
                                           rs.getString("SGL_UF")
                                   )
                            ),
                    idMunicipio
            );
    }

    public List<Municipio> listarMunicipios() {
        return jdbcTemplate.query(
                CONSULTA_MUNICIPIO
                , (rs, rowNum) ->
                        new Municipio(
                                rs.getInt("COD_MUNICIPIO"),
                                rs.getString("NME_MUNICIPIO"),
                                new Estado(
                                        rs.getInt("COD_ESTADO"),
                                        rs.getString("NME_ESTADO"),
                                        rs.getString("SGL_UF")
                                )
                        )
        );
    }

    public void atualizarMunicipio(Municipio municipio)throws DataIntegrityViolationException {
        jdbcTemplate.update(
                 UPDATE_MUNICIPIO + FILTRO_MUNICIPIO_CODIGO,
                municipio.getNomeMunicipio(),
                municipio.getEstado().getCodEstado(),
                municipio.getCodMunicipio()
        );
    }

    public void deletarMunicipio(int idMunicipio)throws DataIntegrityViolationException {
        jdbcTemplate.update(
                "DELETE FROM MUNICIPIO WHERE COD_MUNICIPIO = ?",
                idMunicipio
        );
    }
}

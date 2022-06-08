package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MunicipioRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public void cadastrarMunicipio(Municipio municipio) {
        jdbcTemplate.update("INSERT INTO MUNICIPIO (COD_MUNICIPIO, NME_MUNICIPIO, COD_ESTADO) " +
                        "VALUES (?, ?, ?)",
                municipio.getCodMunicipio(),
                municipio.getCodMunicipio(),
                municipio.getCodEstado()
        );
    }

    public Municipio buscarMunicipioPorId(int idMunicipio) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT COD_MUNICIPIO,NME_MUNICIPIO, COD_ESTADO FROM Municipio WHERE COD_Municipio = ?"
                    , (rs, rowNum) ->
                            new Municipio(
                                    rs.getInt("COD_MUNICIPIO"),
                                    rs.getString("NME_MUNICIPIO"),
                                    rs.getInt("COD_ESTADO")
                            ),
                    idMunicipio
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Municipio> listarMunicipios() {
        return jdbcTemplate.query(
                "SELECT COD_Municipio,NME_Municipio, SGL_UF FROM Municipio"
                , (rs, rowNum) ->
                        new Municipio(
                                rs.getInt("COD_Municipio"),
                                rs.getString("NME_Municipio"),
                                rs.getInt("COD_ESTADO")
                        )
        );
    }

    public void atualizarMunicipios(Municipio municipio) {
        jdbcTemplate.update(
                "UPDATE MUNICIPIO SET NME_MUNICIPIO = ?, COD_ESTADO = ?" +
                        " WHERE COD_MUNICIPIO = ?",
                municipio.getNomeMunicipio(),
                municipio.getCodEstado(),
                municipio.getCodMunicipio()
        );
    }

    public void deletarMunicipio(int idMunicipio) {
        jdbcTemplate.update(
                "DELETE FROM MUNICIPIO WHERE COD_MUNICIPIO = ?",
                idMunicipio
        );
    }
}

package br.com.univesp.mercadocell.mercadocell.repository;

import java.util.List;

import br.com.univesp.mercadocell.mercadocell.model.Bairro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.univesp.mercadocell.mercadocell.model.Bairro;

@Repository
public class BairroRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void cadastrarBairro(Bairro bairro) {
        jdbcTemplate.update(
                "INSERT INTO `Bairro` (NME_BAIRRO, COD_MUNICIPIO) VALUES (?, ?)",
                bairro.getCodBairro(),
                bairro.getCodMunicipio()
        );
    }

    public Bairro buscarBairroPorId(int idBairro) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM BAIRRO WHERE `COD_BAIRRO` = ?"
                    , (resultSet, rowNum) ->
                            new Bairro(
                                    resultSet.getInt("COD_Bairro"),
                                    resultSet.getString("NME_BAIRRO"),
                                    resultSet.getInt("COD_MUNICIPIO")
                            ),
                    new Object[]{idBairro}
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Bairro> listarBairros() {
        return jdbcTemplate.query("SELECT COD_BAIRRO, NME_BAIRRO, COD_MUNICIPIO FROM `Bairro`"
                , (resultSet, rowNum) ->
                        new Bairro(
                                resultSet.getInt("COD_BAIRRO"),
                                resultSet.getString("NME_BAIRRO"),
                                resultSet.getInt("COD_MUNICIPIO")
                        )
        );
    }

    public void atualizarBairro(Bairro bairro) {
        jdbcTemplate.update(
                "UPDATE `BAIRRO` SET `NME_BAIRRO` = ?, COD_MUNICIPIO = ? " +
                        " WHERE `COD_BAIRRO` = ?",
                bairro.getNomeBairro(),
                bairro.getCodMunicipio(),
                bairro.getCodBairro()
        );
    }

    public void deletarBairro(int idBairro) {
        jdbcTemplate.update(
                "DELETE FROM `Bairro` WHERE `COD_BAIRRO` = ?",
                idBairro
        );
    }
}
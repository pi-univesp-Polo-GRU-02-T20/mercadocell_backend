package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Bairro;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BairroRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String COLUNA_COD_BAIRRO ="COD_BAIRRO";
    private static final String COLUNA_NME_BAIRRO ="NME_BAIRRO";
    private static final String COLUNA_COD_MUNICIPIO ="COD_MUNICIPIO";

    public void cadastrarBairro(Bairro bairro) {
        jdbcTemplate.update(
                "INSERT INTO `Bairro` (NME_BAIRRO, COD_MUNICIPIO) VALUES (?, ?)",
                bairro.getCodBairro(),
                bairro.getCodMunicipio()
        );
    }

    public Bairro buscarBairroPorId(int idBairro) {
        try {
            return jdbcTemplate.queryForObject("SELECT COD_BAIRRO, NME_BAIRRO, COD_MUNICIPIO FROM BAIRRO WHERE `COD_BAIRRO` = ?"
                    , (resultSet, rowNum) ->
                            new Bairro(
                                    resultSet.getInt(COLUNA_COD_BAIRRO),
                                    resultSet.getString(COLUNA_NME_BAIRRO),
                                    resultSet.getInt(COLUNA_COD_MUNICIPIO)
                            ),
                    idBairro
            );
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Código de Bairro não encontrado: " + idBairro);
        }
    }

    public Bairro buscarBairroPorNome(String nomeBairro) {
        try {
            return jdbcTemplate.queryForObject(
                        "SELECT COD_BAIRRO, NME_BAIRRO, COD_MUNICIPIO FROM BAIRRO WHERE `NME_BAIRRO` = ?"
                    , (resultSet, rowNum) ->
                            new Bairro(
                                    resultSet.getInt(COLUNA_COD_BAIRRO),
                                    resultSet.getString(COLUNA_NME_BAIRRO),
                                    resultSet.getInt(COLUNA_COD_MUNICIPIO)
                            ),
                    nomeBairro
            );
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Bairro> listarBairros() {
        try{
            return jdbcTemplate.query("SELECT COD_BAIRRO, NME_BAIRRO, COD_MUNICIPIO FROM `BAIRRO`"
                    , (resultSet, rowNum) ->
                            new Bairro(
                                    resultSet.getInt(COLUNA_COD_BAIRRO),
                                    resultSet.getString(COLUNA_NME_BAIRRO),
                                    resultSet.getInt(COLUNA_COD_MUNICIPIO)
                            )
            );
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Nenhum bairro encontrado");
        }
    }

    public void atualizarBairro(Bairro bairro) throws DataIntegrityViolationException {
            jdbcTemplate.update(
                    "UPDATE `BAIRRO` SET `NME_BAIRRO` = ?, COD_MUNICIPIO = ? " +
                            " WHERE `COD_BAIRRO` = ?",
                    bairro.getNomeBairro(),
                    bairro.getCodMunicipio(),
                    bairro.getCodBairro()
            );
    }

    public void deletarBairro(int idBairro) throws DataIntegrityViolationException{
        jdbcTemplate.update(
                "DELETE FROM `Bairro` WHERE `COD_BAIRRO` = ?",
                idBairro
        );
    }
}
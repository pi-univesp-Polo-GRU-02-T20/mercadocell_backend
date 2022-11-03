package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Bairro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
                "INSERT INTO Bairro (NME_BAIRRO, COD_MUNICIPIO) VALUES (?, ?)",
                bairro.getNomeBairro(),
                bairro.getCodMunicipio()
        );
    }

    public Bairro buscarBairroPorId(int idBairro) {
        return jdbcTemplate.queryForObject("SELECT COD_BAIRRO, NME_BAIRRO, COD_MUNICIPIO FROM BAIRRO WHERE COD_BAIRRO = ?"
                , (resultSet, rowNum) ->
                        new Bairro(
                                resultSet.getInt(COLUNA_COD_BAIRRO),
                                resultSet.getString(COLUNA_NME_BAIRRO),
                                resultSet.getInt(COLUNA_COD_MUNICIPIO)
                        ),
                idBairro
        );
    }

    public Bairro buscarBairroPorNome(String nomeBairro){
        return jdbcTemplate.queryForObject(
                    "SELECT COD_BAIRRO, NME_BAIRRO, COD_MUNICIPIO FROM BAIRRO WHERE NME_BAIRRO = ?"
                , (resultSet, rowNum) ->
                        new Bairro(
                                resultSet.getInt(COLUNA_COD_BAIRRO),
                                resultSet.getString(COLUNA_NME_BAIRRO),
                                resultSet.getInt(COLUNA_COD_MUNICIPIO)
                        ),
                nomeBairro
        );
    }

    public List<Bairro> listarBairros() {
        return jdbcTemplate.query("SELECT COD_BAIRRO, NME_BAIRRO, COD_MUNICIPIO FROM BAIRRO"
        , (resultSet, rowNum) ->
                new Bairro(
                        resultSet.getInt(COLUNA_COD_BAIRRO),
                        resultSet.getString(COLUNA_NME_BAIRRO),
                        resultSet.getInt(COLUNA_COD_MUNICIPIO)
                )
        );
    }

    public void atualizarBairro(Bairro bairro) throws DataIntegrityViolationException {
        jdbcTemplate.update(
                "UPDATE BAIRRO SET NME_BAIRRO = ?, COD_MUNICIPIO = ? " +
                        " WHERE COD_BAIRRO = ?",
                bairro.getNomeBairro(),
                bairro.getCodMunicipio(),
                bairro.getCodBairro()
        );
    }

    public void deletarBairro(int idBairro) throws DataIntegrityViolationException{
        jdbcTemplate.update(
                "DELETE FROM Bairro WHERE COD_BAIRRO = ?",
                idBairro
        );
    }
}
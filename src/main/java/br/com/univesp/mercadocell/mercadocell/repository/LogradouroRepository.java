package br.com.univesp.mercadocell.mercadocell.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.univesp.mercadocell.mercadocell.model.Logradouro;

@Repository
public class LogradouroRepository { 

    @Autowired
    JdbcTemplate jdbcTemplate;
    public void cadastrarLogradouro(Logradouro logradouro) {
           jdbcTemplate.update("INSERT INTO LOGRADOURO (COD_CEP, DSC_LOGRADOURO) " +
                           "VALUES (?, ?)",
                            logradouro.getCodCEP(),
                            logradouro.getDescricaoLogradouro()                            
            );
    }

    public Logradouro buscarLogradouroPorId(int idLogradouro) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT COD_CEP, DSC_LOGRADOURO FROM `LOGRADOURO` WHERE `COD_CEP` = ?"
                    , (rs, rowNum) ->
                        new Logradouro(
                                    rs.getInt("COD_CEP"),
                                    rs.getString("DSC_LOGRADOURO")                                    
                            ),
                    idLogradouro
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Logradouro> listarLogradouros() {
        return jdbcTemplate.query(
                "SELECT COD_CEP, DSC_LOGRADOURO FROM `LOGRADOURO`"
                , (rs, rowNum) ->
                        new Logradouro(
                                rs.getInt("COD_CEP"),
                                rs.getString("DSC_LOGRADOURO")
                        )
        );
    }

    public void atualizarLogradouro(Logradouro logradouro) {
        jdbcTemplate.update(
                "UPDATE `LOGRADOURO` SET `COD_CEP` = ?, DSC_LOGRADOURO = ? WHERE COD_CEP = ?",
                logradouro.getCodCEP(),
                logradouro.getDescricaoLogradouro()
        );
    }

    public void deletarLogradouro(int idLogradouro) {
        jdbcTemplate.update(
                "DELETE FROM LOGRADOURO WHERE COD_CEP = ?",
                idLogradouro
        );
    }

}

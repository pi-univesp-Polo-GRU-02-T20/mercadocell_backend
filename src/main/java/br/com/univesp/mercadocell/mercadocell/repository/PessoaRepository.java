package br.com.univesp.mercadocell.mercadocell.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.univesp.mercadocell.mercadocell.model.Pessoa;

@Repository
public class PessoaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void cadastrarPessoa(Pessoa pessoa) {
      /*  jdbcTemplate.update(
                "INSERT INTO PESSOA (NME_PESSOA, DSC_LOGIN, DSC_SENHA, FLG_ATIVO) VALUES (?, ?, ?, ?)",
                pessoa.getnome
        );
        */

    }

    /*
    *   COD_PESSOA
        NME_USUARIO
        DSC_LOGIN
        DSC_SENHA
        FLG_ATIVO
    * */

    public Pessoa buscarPessoaPorId(int idpessoa) {
        /*
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM `pessoa` WHERE `COD_pessoa` = ?"
                    , (rs, rowNum) ->
                            new Pessoa(
                                    rs.getInt("COD_pessoa"),
                                    rs.getString("NME_pessoa")
                            ),
                    new Object[]{idpessoa}
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
        */
        return null;
    }

    public List<Pessoa> listarPessoas() {
        /*return jdbcTemplate.query("SELECT * FROM `pessoa`"
                , (rs, rowNum) ->
                        new Pessoa(
                                rs.getInt("COD_pessoa"),
                                rs.getString("NME_pessoa")
                        )
        );*/
        return  null;
    }

    public void atualizarPessoa(Pessoa pessoa) {
        /*jdbcTemplate.update(
                "UPDATE `pessoa` SET `NME_pessoa` = ? WHERE `pessoa`.`COD_pessoa` = ?",
                pessoa.getNomepessoa(),
                pessoa.getCodpessoa()
        );
        */

    }

    public void deletarPessoa(int idPessoa) {

        jdbcTemplate.update(
                "DELETE FROM `PESSOA` WHERE `pessoa`.`COD_pessoa` = ?",
                idPessoa
        );
    }
}

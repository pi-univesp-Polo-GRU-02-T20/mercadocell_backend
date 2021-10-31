package br.com.univesp.mercadocell.mercadocell.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.univesp.mercadocell.mercadocell.model.Bairro;

@Repository
public class BairroRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    ////todo ADICIONAR TRATAMENTO DE EXCEÇÕES DE BANCO
    public void cadastrarBairro(Bairro bairro, BairroRepository bairroRepository) {

    }
    public Object buscarBairroPorId(Integer codBairro) {
        return null;
}
    public List<Bairro> listarBairros() {
        return null;
    }
    public void atualizarBairro(Bairro bairro) {
    }
    public void deletarBairro(int idBairro) {
    }
}
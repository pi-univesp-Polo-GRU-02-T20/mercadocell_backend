package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Endereco;
import br.com.univesp.mercadocell.mercadocell.model.Logradouro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnderecoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public void cadastrarEndereco(Endereco endereco) {


    }

    public Endereco buscarEnderecoPorId(int codEndereco) {
        return  null;
    }

    public List<Endereco> listarEnderecos() {
        return null;
    }

    public void atualizarEndereco(Endereco endereco) {

    }

    public void deletarEndereco(int codEndereco) {

    }

    public Endereco buscarEnderecoPorCodPessoa(int codPessoa) {
        return null;
    }
}

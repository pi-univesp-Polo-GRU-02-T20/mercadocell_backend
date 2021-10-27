package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Estado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    ////todo ADICIONAR TRATAMENTO DE EXCEÇÕES DE BANCO
    public void cadastrarEstado(Estado estado) {
           jdbcTemplate.update("CALL SP_CADASTRAR_ESTADO(?, ?)",
                    estado.getNomeEstado(), estado.getSiglaUF(), estado.getCodEstado()
            );
    }

public List<Estado> listarEstados() {
        return null;
}

public void atualizarEstados(Estado estado) {
}

public void deletarEstado(int idEstado) {
}  

}

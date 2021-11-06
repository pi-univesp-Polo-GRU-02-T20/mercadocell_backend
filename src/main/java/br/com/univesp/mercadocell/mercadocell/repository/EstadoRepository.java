package br.com.univesp.mercadocell.mercadocell.repository;

import java.util.List;

import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.univesp.mercadocell.mercadocell.model.Estado;

@Repository
public class EstadoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public void cadastrarEstado(Estado estado) {
           jdbcTemplate.update("INSERT INTO ESTADO (COD_ESTADO, NME_ESTADO, SGL_UF) " +
                           "VALUES (?, ?, ?)",
                            estado.getCodEstado(),
                            estado.getNomeEstado(),
                            estado.getSiglaUF()
            );
    }

    public Estado buscarEstadoPorId(int idEstado) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT COD_ESTADO,NME_ESTADO, SGL_UF FROM `ESTADO` WHERE `COD_ESTADO` = ?"
                    , (rs, rowNum) ->
                            new Estado(
                                    rs.getInt("COD_ESTADO"),
                                    rs.getString("NME_ESTADO"),
                                    rs.getString("SGL_UF")
                            ),
                    new Object[]{idEstado}
            );
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Código de Estado não encontrada: " + idEstado);
        }
    }

    public List<Estado> listarEstados() {
        try{
            return jdbcTemplate.query(
                    "SELECT COD_ESTADO,NME_ESTADO, SGL_UF FROM `ESTADO`"
                    , (rs, rowNum) ->
                            new Estado(
                                    rs.getInt("COD_ESTADO"),
                                    rs.getString("NME_ESTADO"),
                                    rs.getString("SGL_UF")
                            )
            );
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Nenhuma estado encontrado");
        }
    }

    public void atualizarEstados(Estado estado) {
        jdbcTemplate.update(
                "UPDATE `ESTADO` SET `NME_ESTADO` = ?, SGL_UF = ? WHERE COD_ESTADO = ?",
                estado.getNomeEstado(),
                estado.getSiglaUF(),
                estado.getCodEstado()
        );
    }

    public void deletarEstado(int idEstado) {
        jdbcTemplate.update(
                "DELETE FROM ESTADO WHERE COD_ESTADO = ?",
                idEstado
        );
    }
}

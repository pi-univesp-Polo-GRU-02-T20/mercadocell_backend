package br.com.univesp.mercadocell.mercadocell.repository;
import br.com.univesp.mercadocell.mercadocell.model.*;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImagemRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_IMAGEM ="SELECT IMG.COD_IMAGEM, IMG.NME_IMAGEM, PR.BNR_IMAGEM," +
            " FROM IMAGEM IMG ";
    private static final String FILTRO_TIPO_IMAGEM = " WHERE TPO_IMAGEM = ?";
    private static final String FILTRO_NOME_IMAGEM = " WHERE NME_IMAGEM = ?";
    private static final String FILTRO_COD_IMAGEM = " WHERE COD_IMAGEM = ?";
    private static final String  INSERT_IMAGEM =    " INSERT INTO IMAGEM (NME_IMAGEM, TPO_IMAGEM, BNR_IMAGEM )" +
                                                    " VALUES (?, ?, ?,) ";
    private static final String  UPDATE_IMAGEM = "UPDATE IMAGEM SET NME_IMAGEM = ? , TPO_IMAGEM = ?, BNR_IMAGEM = ? ";
    private static final String  DELETE_IMAGEM = "DELETE FROM IMAGEM ";

    //COLUNAS
    private static final String COL_COD_IMAGEM = "COD_IMAGEM";
    private static final String COL_NOME_IMAGEM = "NME_IMAGEM";
    private static final String COL_TIPO_IMAGEM = "TPO_IMAGEM";
    private static final String COL_BINARIO_IMAGEM = "BNR_IMAGEM";

        public void cadastrarImagem(Imagem imagem) throws EntityIntegrityViolationException {
        jdbcTemplate.update(
                INSERT_IMAGEM,
                imagem.getNomeImagem(),
                imagem.getTipoImagem(),
                imagem.getBinarioImagem()
        );
    }

    public Imagem buscarImagemPorId(int idImagem){
        return jdbcTemplate.queryForObject(
                SELECT_IMAGEM + FILTRO_COD_IMAGEM
                , (rs, rowNum) ->
                        new Imagem(
                                rs.getInt(COL_COD_IMAGEM),
                                rs.getString(COL_NOME_IMAGEM),
                                rs.getString(COL_TIPO_IMAGEM),
                                rs.getBytes(COL_BINARIO_IMAGEM)
                        ),
                idImagem
        );
    }

    public List<Imagem> listarImagens(){
        return jdbcTemplate.query(SELECT_IMAGEM
                , (rs, rowNum) ->
                        new Imagem(
                                rs.getInt(COL_COD_IMAGEM),
                                rs.getString(COL_NOME_IMAGEM),
                                rs.getString(COL_TIPO_IMAGEM),
                                rs.getBytes(COL_BINARIO_IMAGEM)
                        )
        );
    }

    public void atualizarImagem(Imagem imagem) throws DataIntegrityViolationException{
        jdbcTemplate.update(
                UPDATE_IMAGEM + FILTRO_COD_IMAGEM,
                imagem.getNomeImagem(),
                imagem.getTipoImagem(),
                imagem.getBinarioImagem()
        );
    }

    public void deletarImagem(int idImagem) throws DataIntegrityViolationException {
        jdbcTemplate.update(
                DELETE_IMAGEM + FILTRO_COD_IMAGEM,
                idImagem
        );
    }
}


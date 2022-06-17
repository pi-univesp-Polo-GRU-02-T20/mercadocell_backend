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

    private static final String SELECT_IMAGEM ="SELECT COD_IMAGEM, NME_IMAGEM, TPO_IMAGEM, BNR_IMAGEM " +
            " FROM IMAGEM ";
    private static final String FILTRO_TIPO_IMAGEM = " WHERE TPO_IMAGEM = ?";
    private static final String FILTRO_NOME_IMAGEM = " WHERE NME_IMAGEM = ?";
    private static final String FILTRO_COD_IMAGEM = " WHERE COD_IMAGEM = ?";
    private static final String  INSERT_IMAGEM =    " INSERT INTO IMAGEM (NME_IMAGEM, TPO_IMAGEM, BNR_IMAGEM )" +
                                                    " VALUES (?, ?, ?) ";
    private static final String  UPDATE_IMAGEM = "UPDATE IMAGEM SET NME_IMAGEM = ? , TPO_IMAGEM = ?, BNR_IMAGEM = ? ";
    private static final String  DELETE_IMAGEM = "DELETE FROM IMAGEM ";

    // VINCULO IMAGEM
    private static final String INSERT_IMAGEM_VINCULO_PRODUTO = " INSERT INTO IMAGEM_PRODUTO " +
            " (COD_PRODUTO, COD_IMAGEM)" +
            " VALUES (?, ?,) ";
    private static final String DELETE_VINCULO_IMAGEM_PRODUTO = "DELETE FROM IMAGEM_PRODUTO " +
            " WHERE COD_PRODUTO = ? AND COD_IMAGEM = ? ";
    private static final String SELECT_IMAGEM_VINCULO_PRODUTO =
            " SELECT IMG.COD_IMAGEM, IMG.NME_IMAGEM, IMG.BNR_IMAGEM, IMG.TPO_IMAGEM " +
            " FROM IMAGEM_PRODUTO IP "+
            " INNER JOIN IMAGEM IMG " +
            " ON IP.COD_IMAGEM = IMG.COD_IMAGEM " ;
    private static final String FILTRO_COD_PRODUTO = " WHERE COD_PRODUTO = ?";
    public static final char INSERIR_IMAGEM_VINCULO = 'C';
    public static final char DELETAR_IMAGEM_VINCULO = 'D';

    //COLUNAS - IMAGEM
    private static final String COL_COD_IMAGEM = "COD_IMAGEM";
    private static final String COL_NOME_IMAGEM = "NME_IMAGEM";
    private static final String COL_TIPO_IMAGEM = "TPO_IMAGEM";
    private static final String COL_BINARIO_IMAGEM = "BNR_IMAGEM";
    // VINCULOS
    private static final String COL_PRODUTO = "COD_PRODUTO";

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

    public Imagem buscarImagemPorNome(String nomeImagem){
        return jdbcTemplate.queryForObject(
                SELECT_IMAGEM + FILTRO_NOME_IMAGEM
                , (rs, rowNum) ->
                        new Imagem(
                                rs.getInt(COL_COD_IMAGEM),
                                rs.getString(COL_NOME_IMAGEM),
                                rs.getString(COL_TIPO_IMAGEM),
                                rs.getBytes(COL_BINARIO_IMAGEM)
                        ),
                nomeImagem
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

    public void removerVinculoImagemProduto(int codigoProduto, int codigoImagem)
            throws EntityIntegrityViolationException {
       jdbcTemplate.update(DELETE_VINCULO_IMAGEM_PRODUTO,
                codigoProduto,
                codigoImagem
        );
    }

    public void inserirVinculoImagemProduto(int codigoProduto, int codigoImagem)
                throws EntityIntegrityViolationException {
            jdbcTemplate.update(INSERT_IMAGEM_VINCULO_PRODUTO,
                    codigoProduto,
                    codigoImagem
            );
    }

    public List<Imagem> buscarImagemProdutoPorId(int idProduto){
       return jdbcTemplate.query(
                SELECT_IMAGEM_VINCULO_PRODUTO + FILTRO_COD_PRODUTO
                , (rs, rowNum) ->
                        new Imagem(
                                rs.getInt(COL_COD_IMAGEM),
                                rs.getString(COL_NOME_IMAGEM),
                                rs.getString(COL_TIPO_IMAGEM),
                                rs.getBytes(COL_BINARIO_IMAGEM)
                        ),
                 idProduto
        );
    }

}


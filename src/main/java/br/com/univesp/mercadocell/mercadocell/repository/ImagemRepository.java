package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.dto.ImagemProdutoDTO;
import br.com.univesp.mercadocell.mercadocell.model.Imagem;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    private static final String  TABELA_IMAGEM =    " IMAGEM";
    private static final String GET_INSERTED_ID = " SELECT MAX(COD_IMAGEM) COD_IMAGEM FROM IMAGEM ";
    private static final String COL_AUTO_INCREMENT = "AUTO_INCREMENT";
    private static final String  UPDATE_IMAGEM = "UPDATE IMAGEM SET NME_IMAGEM = ? , TPO_IMAGEM = ?, BNR_IMAGEM = ? ";
    private static final String  DELETE_IMAGEM = "DELETE FROM IMAGEM ";

    // VINCULO IMAGEM
    private static final String INSERT_IMAGEM_VINCULO_PRODUTO = " INSERT INTO IMAGEM_PRODUTO " +
            " (COD_IMAGEM, COD_PRODUTO)" +
            " VALUES (?, ?) ";


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
    private static final String COL_COD_PRODUTO = "COD_PRODUTO";


    public void cadastrarImagem(Imagem imagem) throws EntityIntegrityViolationException {
        jdbcTemplate.update(
                INSERT_IMAGEM,
                imagem.getNomeImagem(),
                imagem.getTipoImagem(),
                imagem.getBinarioImagem()
        );
   }


    // cadastro de imagem modelo 1:N
    public int cadastrarImagem(Integer produtoId, Imagem imagem) throws EntityIntegrityViolationException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
           @Override
           public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
               PreparedStatement statement = con.prepareStatement(INSERT_IMAGEM,
                       new String[]{COL_NOME_IMAGEM, COL_TIPO_IMAGEM, COL_BINARIO_IMAGEM});
               statement.setString(1, imagem.getNomeImagem());
               statement.setString(2, imagem.getTipoImagem());
               statement.setBytes(3, imagem.getBinarioImagem());
               return statement;
           }
       }, keyHolder);
        return keyHolder.getKey().intValue();//nosonar
   /*
        ImagemProdutoDTO imagemProdutoDTO = new ImagemProdutoDTO(
                keyHolder.getKey().intValue(),//nosonar
               produtoId
       );
        System.out.println(imagemProdutoDTO.toString());
        vincularImagemProduto(imagemProdutoDTO   );

    */
    }

    public int getCodImagemCadastrada() throws EntityIntegrityViolationException {
        Integer codImagem =  jdbcTemplate.queryForObject(GET_INSERTED_ID, Integer.class);
        return  codImagem == null ? 0: codImagem;
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

    public void vincularImagemProduto(ImagemProdutoDTO imagemProdutoDTO)
                throws EntityIntegrityViolationException {
            jdbcTemplate.update(INSERT_IMAGEM_VINCULO_PRODUTO,
                    imagemProdutoDTO.getCodigoImagem(),
                    imagemProdutoDTO.getCodigoProduto()
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


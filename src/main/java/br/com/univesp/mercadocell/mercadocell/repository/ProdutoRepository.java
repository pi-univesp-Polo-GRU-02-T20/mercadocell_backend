package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.*;
import br.com.univesp.mercadocell.mercadocell.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static String SELECT_PRODUTO ="SELECT PR.COD_PRODUTO, PR.NME_PRODUTO, PR.DSC_PRODUTO "+
            ", SC.COD_SUBCATEGORIA, SC.NME_SUBCATEGORIA "+
            ", CT.COD_CATEGORIA, CT.NME_CATEGORIA "+
            ", UM.COD_UNIDADE_MEDIDA, UM.NME_UNIDADE_MEDIDA, UM.SGL_UNIDADE_MEDIDA "+
            " FROM PRODUTO PR "+
            " INNER JOIN SUBCATEGORIA 	    SC ON PR.COD_SUBCATEGORIA 	=	SC.COD_SUBCATEGORIA "+
            " INNER JOIN CATEGORIA 		    CT ON CT.COD_CATEGORIA 		= 	SC.COD_CATEGORIA "+
            " INNER JOIN UNIDADE_MEDIDA 	UM ON UM.COD_UNIDADE_MEDIDA = 	PR.COD_UNIDADE_MEDIDA ";
    private static String FILTRO_COD_PRODUTO = " WHERE COD_PRODUTO = ?";
    private static String FILTRO_NOME_PRODUTO = " WHERE NME_PRODUTO = ?";

    public void cadastrarProduto(Produto produto){
        jdbcTemplate.update("INSERT INTO PRODUTO " + 
                        "(NME_PRODUTO, DSC_PRODUTO, COD_SUBCATEGORIA, COD_UNIDADE_MEDIDA )" +
                        " VALUES (?, ?, ?, ?)",
                produto.getNomeProduto(),
                produto.getDescricaoProduto(),
                produto.getSubCategoria().getCodSubCategoria(),
                produto.getUnidadeMedida().getCodUnidadeMedida()
        );
    }

    public Produto buscarProdutoPorId(int idProduto){
        return jdbcTemplate.queryForObject(
                    SELECT_PRODUTO + FILTRO_COD_PRODUTO
                    , (rs, rowNum) ->
                            new Produto(
                                    rs.getInt("COD_PRODUTO"),
                                    rs.getString("NME_PRODUTO"),
                                    rs.getString("DSC_PRODUTO"),
                                    new SubCategoria(
                                                rs.getInt("COD_SUBCATEGORIA"),
                                                rs.getString("NME_SUBCATEGORIA"),
                                            new Categoria(
                                                    rs.getInt("COD_CATEGORIA"),
                                                    rs.getString("NME_CATEGORIA")
                                            )
                                    ),
                                    new UnidadeMedida(
                                            rs.getInt("COD_SUBCATEGORIA"),
                                            rs.getString("NME_UNIDADE_MEDIDA"),
                                            rs.getString("SGL_UNIDADE_MEDIDA")
                                    )
                            ),
                    new Object[]{idProduto}
            );
    }

    public Categoria buscarProdutoPorNome(String nomeProduto) {
        return jdbcTemplate.queryForObject(SELECT_PRODUTO + FILTRO_NOME_PRODUTO
                , (rs, rowNum) ->
                        new Categoria(
                                rs.getInt("COD_CATEGORIA"),
                                rs.getString("NME_CATEGORIA")
                        ),
                new Object[]{nomeProduto}
        );
    }
    public List<Produto> listarProdutos(){
        return jdbcTemplate.query(SELECT_PRODUTO
                , (rs, rowNum) ->
                        new Produto(
                                rs.getInt("COD_PRODUTO"),
                                rs.getString("NME_PRODUTO"),
                                rs.getString("DSC_PRODUTO"),
                                new SubCategoria(
                                        rs.getInt("COD_SUBCATEGORIA"),
                                        rs.getString("NME_SUBCATEGORIA"),
                                        new Categoria(
                                                rs.getInt("COD_CATEGORIA"),
                                                rs.getString("NME_CATEGORIA")
                                        )
                                ),
                                new UnidadeMedida(
                                        rs.getInt("COD_SUBCATEGORIA"),
                                        rs.getString("NME_UNIDADE_MEDIDA"),
                                        rs.getString("SGL_UNIDADE_MEDIDA")
                                )
                        )
        );
    }

    public void atualizarProduto(Produto produto) throws DataIntegrityViolationException{
        String update = "UPDATE `PRODUTO` SET `NME_PRODUTO` = ? , DSC_PRODUTO = ?, "
                + " COD_SUBCATEGORIA = ? , COD_UNIDADE_MEDIDA = ? "
                + FILTRO_COD_PRODUTO;
        jdbcTemplate.update(
                    update,
                produto.getNomeProduto(),
                produto.getDescricaoProduto(),
                produto.getSubCategoria().getCodSubCategoria(),
                produto.getUnidadeMedida().getCodUnidadeMedida(),
                produto.getCodProduto()
        );
    }

    public void deletarProduto(int idProduto) throws DataIntegrityViolationException {
        jdbcTemplate.update(
                "DELETE FROM `PRODUTO` " + FILTRO_COD_PRODUTO,
                idProduto
        );
    }
}

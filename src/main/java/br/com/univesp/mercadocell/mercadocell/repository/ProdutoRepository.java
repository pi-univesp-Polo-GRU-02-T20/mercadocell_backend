package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import br.com.univesp.mercadocell.mercadocell.model.Produto;
import br.com.univesp.mercadocell.mercadocell.model.SubCategoria;
import br.com.univesp.mercadocell.mercadocell.model.UnidadeMedida;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_PRODUTO ="SELECT PR.COD_PRODUTO, PR.NME_PRODUTO, PR.DSC_PRODUTO, PR.QTD_ESTOQUE_MIN "+
            ", SC.COD_SUBCATEGORIA, SC.NME_SUBCATEGORIA "+
            ", CT.COD_CATEGORIA, CT.NME_CATEGORIA "+
            ", UM.COD_UNIDADE_MEDIDA, UM.NME_UNIDADE_MEDIDA, UM.SGL_UNIDADE_MEDIDA "+
            " FROM PRODUTO PR "+
            " INNER JOIN SUBCATEGORIA SC ON PR.COD_SUBCATEGORIA = SC.COD_SUBCATEGORIA "+
            " INNER JOIN CATEGORIA CT ON CT.COD_CATEGORIA = SC.COD_CATEGORIA "+
            " INNER JOIN UNIDADE_MEDIDA UM ON UM.COD_UNIDADE_MEDIDA = PR.COD_UNIDADE_MEDIDA ";
    private static final String FILTRO_COD_PRODUTO = " WHERE COD_PRODUTO = ?";
    private static final String FILTRO_NOME_PRODUTO = " WHERE NME_PRODUTO = ?";
    private static final String COL_COD_SUBCATEGORIA = "COD_SUBCATEGORIA";
    private static final String COL_NME_SUBCATEGORIA = "NME_SUBCATEGORIA";
    private static final String COL_COD_CATEGORIA = "COD_CATEGORIA";
    private static final String COL_NME_CATEGORIA = "NME_CATEGORIA";
    private static final String COL_COD_PRODUTO = "COD_PRODUTO";
    private static final String COL_DSC_PRODUTO = "DSC_PRODUTO";
    private static final String COL_NME_PRODUTO = "NME_PRODUTO";
    private static final String COL_NME_UNIDADE_MEDIDA = "NME_UNIDADE_MEDIDA";
    private static final String COL_SGL_UNIDADE_MEDIDA = "SGL_UNIDADE_MEDIDA";
    private static final String COL_QTD_ESTOQUE_MIN ="QTD_ESTOQUE_MIN";



    public void cadastrarProduto(Produto produto) throws EntityIntegrityViolationException {
        jdbcTemplate.update("INSERT INTO PRODUTO " + 
                        "(NME_PRODUTO, DSC_PRODUTO, COD_SUBCATEGORIA, COD_UNIDADE_MEDIDA, QTD_MIN_ESTOQUE )" +
                        " VALUES (?, ?, ?, ?, ?)",
                produto.getNomeProduto(),
                produto.getDescricaoProduto(),
                produto.getSubCategoria().getCodSubCategoria(),
                produto.getUnidadeMedida().getCodUnidadeMedida(),
                produto.getQuantidadeEstoqueMinima()
        );
    }

    public Produto buscarProdutoPorId(int idProduto){
        return jdbcTemplate.queryForObject(
                    SELECT_PRODUTO + FILTRO_COD_PRODUTO
                    , (rs, rowNum) ->
                            new Produto(
                                    rs.getInt(COL_COD_PRODUTO),
                                    rs.getString(COL_NME_PRODUTO),
                                    rs.getString(COL_DSC_PRODUTO),
                                    new SubCategoria(
                                                rs.getInt(COL_COD_SUBCATEGORIA),
                                                rs.getString(COL_NME_SUBCATEGORIA),
                                            new Categoria(
                                                    rs.getInt(COL_COD_CATEGORIA),
                                                    rs.getString(COL_NME_CATEGORIA)
                                            )
                                    ),
                                    new UnidadeMedida(
                                            rs.getInt(COL_COD_SUBCATEGORIA),
                                            rs.getString(COL_NME_UNIDADE_MEDIDA),
                                            rs.getString(COL_SGL_UNIDADE_MEDIDA)
                                    ),
                                    rs.getInt(COL_QTD_ESTOQUE_MIN)
                            ),
                    idProduto
            );
    }

    public Categoria buscarProdutoPorNome(String nomeProduto) {
        return jdbcTemplate.queryForObject(SELECT_PRODUTO + FILTRO_NOME_PRODUTO
                , (rs, rowNum) ->
                        new Categoria(
                                rs.getInt(COL_COD_CATEGORIA),
                                rs.getString(COL_NME_CATEGORIA)
                        ),
                nomeProduto
        );
    }
    public List<Produto> listarProdutos(){
        return jdbcTemplate.query(SELECT_PRODUTO
                , (rs, rowNum) ->
                        new Produto(
                                rs.getInt(COL_COD_PRODUTO),
                                rs.getString(COL_NME_PRODUTO),
                                rs.getString(COL_DSC_PRODUTO),
                                new SubCategoria(
                                        rs.getInt(COL_COD_SUBCATEGORIA),
                                        rs.getString(COL_NME_SUBCATEGORIA),
                                        new Categoria(
                                                rs.getInt(COL_COD_CATEGORIA),
                                                rs.getString(COL_NME_CATEGORIA)
                                        )
                                ),
                                new UnidadeMedida(
                                        rs.getInt(COL_COD_SUBCATEGORIA),
                                        rs.getString(COL_NME_UNIDADE_MEDIDA),
                                        rs.getString(COL_SGL_UNIDADE_MEDIDA)
                                ),
                                rs.getInt(COL_QTD_ESTOQUE_MIN)
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

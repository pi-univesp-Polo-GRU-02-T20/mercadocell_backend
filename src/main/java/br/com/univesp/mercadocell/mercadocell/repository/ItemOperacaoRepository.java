package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ItemOperacaoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_ITEM_PRODUTO =
            " SELECT PR.COD_PRODUTO, PR.NME_PRODUTO, PR.DSC_PRODUTO, PR.QTD_ESTOQUE_MIN "+
            ", SC.COD_SUBCATEGORIA, SC.NME_SUBCATEGORIA "+
            ", CT.COD_CATEGORIA, CT.NME_CATEGORIA "+
            ", UM.COD_UNIDADE_MEDIDA, UM.NME_UNIDADE_MEDIDA, UM.SGL_UNIDADE_MEDIDA "+
            ", OP.COD_OPERACAO, OP.DTA_OPERACAO, OP.VLR_TOTAL,OP.COD_NOTA_FISCAL, OP.TPO_STATUS"+
            ", IOP.COD_ITEM_OPERACAO,IOP.QTD_ITEM, IOP.VLR_ITEM,IOP.COD_OPERACAO, " +
            " tp.COD_TIPO_PAGAMENTO, TP.NME_TIPO_PAGAMENTO "+
            " FROM PRODUTO PR "+
            " INNER JOIN SUBCATEGORIA   SC ON PR.COD_SUBCATEGORIA    = SC.COD_SUBCATEGORIA " +
            " INNER JOIN CATEGORIA      CT  ON CT.COD_CATEGORIA      = SC.COD_CATEGORIA " +
            " INNER JOIN UNIDADE_MEDIDA UM  ON UM.COD_UNIDADE_MEDIDA = PR.COD_UNIDADE_MEDIDA "+
            " INNER JOIN ITEM_OPERACAO  IOP ON  PR.COD_PRODUTO       = IOP.COD_PRODUTO " +
            " INNER JOIN OPERACAO       OP  ON OP.COD_OPERACAO       = IOP.COD_OPERACAO " +
            " INNER JOIN TIPO_PAGAMENTO TP  ON TP.COD_TIPO_PAGAMENTO = OP.COD_TIPO_PAGAMENTO "
            ;
    private static final String COL_DTA_OPERACAO = "DTA_OPERACAO";
    private static final String COL_VLR_TOTAL = "VLR_TOTAL";
    private static final String COL_COD_TIPO_PAGAMENTO = "COD_TIPO_PAGAMENTO";
    private static final String COL_COD_SUBCATEGORIA = "COD_SUBCATEGORIA";
    private static final String COL_COD_OPERACAO = "COD_OPERACAO";
    private static final String COL_COD_UNIDADE_MEDIDA = "COD_UNIDADE_MEDIDA";
    private static final String COL_COD_PRODUTO = "COD_PRODUTO";
    private static final String COL_COD_CATEGORIA = "COD_CATEGORIA";
    private static final String COL_QTD_ITEM = "QTD_ITEM";
    private static final String COL_NME_UNIDADE_MEDIDA = "NME_UNIDADE_MEDIDA";
    private static final String COL_VLR_ITEM = "VLR_ITEM";
    private static final String COL_COD_NOTA_FISCAL = "COD_NOTA_FISCAL";
    private static final String COL_NME_PRODUTO = "NME_PRODUTO";
    private static final String COL_TPO_STATUS = "TPO_STATUS";
    private static final String COL_NME_SUBCATEGORIA = "NME_SUBCATEGORIA";
    private static final String COL_DSC_PRODUTO = "DSC_PRODUTO";
    private static final String COL_QTD_ESTOQUE_MIN ="QTD_ESTOQUE_MIN";
    private static final String COL_QTD_ESTOQUE_MAX ="QTD_ESTOQUE_MAX";
    private static final String COL_QTD_ESTOQUE_ATUAL ="QTD_ESTOQUE_ATUAL";
    private static final String COL_NME_CATEGORIA = "NME_CATEGORIA";
    private static final String COL_COD_ITEM_OPERACAO = "COD_ITEM_OPERACAO";
    private static final String COL_NME_TIPO_PAGAMENTO = "NME_TIPO_PAGAMENTO";


     public List<ItemOperacao> listarOperacoesPorCategoria(String nomeCategoria, String tipoOperacao) {
        return jdbcTemplate.query(
                SELECT_ITEM_PRODUTO + "WHERE CT.NME_CATEGORIA LIKE ? AND OP.TPO_OPERACAO = ?"
                , (resultSet, rowNum) ->
                        new ItemOperacao(
                                resultSet.getInt(COL_COD_ITEM_OPERACAO),
                                resultSet.getFloat(COL_QTD_ITEM),
                                resultSet.getDouble(COL_VLR_ITEM),
                                new Operacao(
                                    resultSet.getInt(COL_COD_OPERACAO),
                                    resultSet.getObject(COL_DTA_OPERACAO, LocalDateTime.class),
                                    resultSet.getString(COL_COD_NOTA_FISCAL),
                                    resultSet.getDouble(COL_VLR_TOTAL),
                                    resultSet.getString(COL_TPO_STATUS),
                                    new TipoPagamento(
                                            resultSet.getInt(COL_COD_TIPO_PAGAMENTO),
                                            resultSet.getString(COL_NME_TIPO_PAGAMENTO)
                                    )
                                ),
                                new Produto(
                                        resultSet.getInt(COL_COD_PRODUTO),
                                        resultSet.getString(COL_NME_PRODUTO),
                                        resultSet.getString(COL_DSC_PRODUTO),
                                        new SubCategoria(
                                                resultSet.getInt(COL_COD_SUBCATEGORIA),
                                                resultSet.getString(COL_NME_SUBCATEGORIA),
                                                new Categoria(
                                                        resultSet.getInt(COL_COD_CATEGORIA),
                                                        resultSet.getString(COL_NME_CATEGORIA)
                                                )
                                        ),
                                        new UnidadeMedida(
                                                resultSet.getInt(COL_COD_UNIDADE_MEDIDA),
                                                resultSet.getString(COL_NME_UNIDADE_MEDIDA)
                                        ),
                                        resultSet.getInt(COL_QTD_ESTOQUE_MIN),
                                        resultSet.getInt(COL_QTD_ESTOQUE_MAX),
                                        resultSet.getInt(COL_QTD_ESTOQUE_ATUAL)
                                )
                        ),
                "%"+nomeCategoria+"%", tipoOperacao
        );
    }

    public List<ItemOperacao> listarItensOperacaoPorProduto(String nomeProduto, String tipoOperacao) {
        return jdbcTemplate.query(
                    SELECT_ITEM_PRODUTO + "WHERE PR.NME_PRODUTO LIKE ? AND OP.TPO_OPERACAO = ?"
                , (resultSet, rowNum) ->
                        new ItemOperacao(
                                resultSet.getInt(COL_COD_ITEM_OPERACAO),
                                resultSet.getFloat(COL_QTD_ITEM),
                                resultSet.getDouble(COL_VLR_ITEM),
                                new Operacao(
                                        resultSet.getInt(COL_COD_OPERACAO),
                                        resultSet.getObject(COL_DTA_OPERACAO, LocalDateTime.class),
                                        resultSet.getString(COL_COD_NOTA_FISCAL),
                                        resultSet.getDouble(COL_VLR_TOTAL),
                                        resultSet.getString(COL_TPO_STATUS),
                                        new TipoPagamento(
                                                resultSet.getInt(COL_COD_TIPO_PAGAMENTO),
                                                resultSet.getString(COL_NME_TIPO_PAGAMENTO)
                                        )
                                ),
                                new Produto(
                                        resultSet.getInt(COL_COD_PRODUTO),
                                        resultSet.getString(COL_NME_PRODUTO),
                                        resultSet.getString(COL_DSC_PRODUTO),
                                        new SubCategoria(
                                                resultSet.getInt(COL_COD_SUBCATEGORIA),
                                                resultSet.getString(COL_NME_SUBCATEGORIA),
                                                new Categoria(
                                                        resultSet.getInt(COL_COD_CATEGORIA),
                                                        resultSet.getString(COL_NME_CATEGORIA)
                                                )
                                        ),
                                        new UnidadeMedida(
                                                resultSet.getInt(COL_COD_UNIDADE_MEDIDA),
                                                resultSet.getString(COL_NME_UNIDADE_MEDIDA)
                                        ),
                                        resultSet.getInt(COL_QTD_ESTOQUE_MIN),
                                        resultSet.getInt(COL_QTD_ESTOQUE_MAX),
                                        resultSet.getInt(COL_QTD_ESTOQUE_ATUAL)
                                )
                        ),
                "%"+nomeProduto+"%", tipoOperacao
        );
    }

    public List<ItemOperacao> listarItensPorOperacao(int idOperacao) {
        return jdbcTemplate.query(//SELECT_ITEM_PRODUTO + "  WHERE IOP.COD_OPERACAO = "+ idOperacao
                SELECT_ITEM_PRODUTO + "  WHERE OP.COD_OPERACAO = ? ; "
                , (resultSet, rowNum) ->
                        new ItemOperacao(
                                resultSet.getInt(COL_COD_ITEM_OPERACAO),
                                resultSet.getFloat(COL_QTD_ITEM),
                                resultSet.getDouble(COL_VLR_ITEM),
                                new Operacao(
                                        resultSet.getInt(COL_COD_OPERACAO),
                                        resultSet.getObject(COL_DTA_OPERACAO, LocalDateTime.class),
                                        resultSet.getString(COL_COD_NOTA_FISCAL),
                                        resultSet.getDouble(COL_VLR_TOTAL),
                                        resultSet.getString(COL_TPO_STATUS),
                                        new TipoPagamento(
                                                resultSet.getInt(COL_COD_TIPO_PAGAMENTO),
                                                resultSet.getString(COL_NME_TIPO_PAGAMENTO)
                                        )
                                ),
                                new Produto(
                                        resultSet.getInt(COL_COD_PRODUTO),
                                        resultSet.getString(COL_NME_PRODUTO),
                                        resultSet.getString(COL_DSC_PRODUTO),
                                        new SubCategoria(
                                                resultSet.getInt(COL_COD_SUBCATEGORIA),
                                                resultSet.getString(COL_NME_SUBCATEGORIA),
                                                new Categoria(
                                                        resultSet.getInt(COL_COD_CATEGORIA),
                                                        resultSet.getString(COL_NME_CATEGORIA)
                                                )
                                        ),
                                        new UnidadeMedida(
                                                resultSet.getInt(COL_COD_UNIDADE_MEDIDA),
                                                resultSet.getString(COL_NME_UNIDADE_MEDIDA)
                                        ),
                                        resultSet.getInt(COL_QTD_ESTOQUE_MIN),
                                        resultSet.getInt(COL_QTD_ESTOQUE_MAX),
                                        resultSet.getInt(COL_QTD_ESTOQUE_ATUAL)
                                )
                        ),
                idOperacao
        );
    }

    public List<ItemOperacao> listarItensOperacaoPorTipoOperacao(String tipoOperacao) {
        return jdbcTemplate.query(
                SELECT_ITEM_PRODUTO + "  WHERE OP.TPO_OPERACAO = ? "
                , (resultSet, rowNum) ->
                        new ItemOperacao(
                                resultSet.getInt(COL_COD_ITEM_OPERACAO),
                                resultSet.getFloat(COL_QTD_ITEM),
                                resultSet.getDouble(COL_VLR_ITEM),
                                new Operacao(
                                        resultSet.getInt(COL_COD_OPERACAO),
                                        resultSet.getObject(COL_DTA_OPERACAO, LocalDateTime.class),
                                        resultSet.getString(COL_COD_NOTA_FISCAL),
                                        resultSet.getDouble(COL_VLR_TOTAL),
                                        resultSet.getString(COL_TPO_STATUS),
                                        new TipoPagamento(
                                                resultSet.getInt(COL_COD_TIPO_PAGAMENTO),
                                                resultSet.getString(COL_NME_TIPO_PAGAMENTO)
                                        )
                                ),
                                new Produto(
                                        resultSet.getInt(COL_COD_PRODUTO),
                                        resultSet.getString(COL_NME_PRODUTO),
                                        resultSet.getString(COL_DSC_PRODUTO),
                                        new SubCategoria(
                                                resultSet.getInt(COL_COD_SUBCATEGORIA),
                                                resultSet.getString(COL_NME_SUBCATEGORIA),
                                                new Categoria(
                                                        resultSet.getInt(COL_COD_CATEGORIA),
                                                        resultSet.getString(COL_NME_CATEGORIA)
                                                )
                                        ),
                                        new UnidadeMedida(
                                                resultSet.getInt(COL_COD_UNIDADE_MEDIDA),
                                                resultSet.getString(COL_NME_UNIDADE_MEDIDA)
                                        ),
                                        resultSet.getInt(COL_QTD_ESTOQUE_MIN),
                                        resultSet.getInt(COL_QTD_ESTOQUE_MAX),
                                        resultSet.getInt(COL_QTD_ESTOQUE_ATUAL)
                                )
                        ),
                tipoOperacao
        );
    }

    public void incluirItemOperacao(ItemOperacao itemOperacao) throws  DataIntegrityViolationException{
        jdbcTemplate.update("INSERT INTO ITEM_OPERACAO (QTD_ITEM, VLR_ITEM, COD_OPERACAO, COD_PRODUTO) " +
                    " VALUES (?, ?, ?, ?)",
            itemOperacao.getQuantidadeItem(),
            itemOperacao.getValorItem(),
            itemOperacao.getOperacao().getCodOperacao(),
            itemOperacao.getProduto().getCodProduto()
        );
    }

    public void removerItemOperacao(br.com.univesp.mercadocell.mercadocell.model.ItemOperacao itemOperacao)
            throws DataIntegrityViolationException {
        jdbcTemplate.update("DELETE FROM ITEM_OPERACAO WHERE COD_ITEM_OPERACAO = ?",
                itemOperacao.getCodItemOperacao()
        );
    }
}

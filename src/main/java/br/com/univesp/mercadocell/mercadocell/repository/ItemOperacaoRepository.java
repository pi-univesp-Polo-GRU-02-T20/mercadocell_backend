package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.*;
import br.com.univesp.mercadocell.mercadocell.model.dto.ItemOperacaoDTO;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ItemOperacaoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_ITEM_PRODUTO =
            "SELECT PR.COD_PRODUTO, PR.NME_PRODUTO, PR.DSC_PRODUTO "+
            ", SC.COD_SUBCATEGORIA, SC.NME_SUBCATEGORIA "+
            ", CT.COD_CATEGORIA, CT.NME_CATEGORIA "+
            ", UM.COD_UNIDADE_MEDIDA, UM.NME_UNIDADE_MEDIDA, UM.SGL_UNIDADE_MEDIDA "+
            ", OP.COD_OPERACAO, OP.DTA_OPERACAO, OP.VLR_TOTAL,OP.COD_NOTA_FISCAL, OP.TPO_STATUS"+
            ", IOP.COD_ITEM_OPERACAO,IOP.QTD_ITEM, IOP.VLR_ITEM,IOP.COD_OPERACAO " +
            " FROM PRODUTO PR "+
            " INNER JOIN SUBCATEGORIA SC ON PR.COD_SUBCATEGORIA     = SC.COD_SUBCATEGORIA "+
            " INNER JOIN CATEGORIA    CT ON CT.COD_CATEGORIA        = SC.COD_CATEGORIA "+
            " INNER JOIN UNIDADE_MEDIDA UM ON UM.COD_UNIDADE_MEDIDA = PR.COD_UNIDADE_MEDIDA "+
            " INNER JOIN ITEM_OPERACAO IOP ON  PR.COD_PRODUTO       = IOP.COD_PRODUTO "+
            " INNER JOIN OPERACAO OP ON OP.COD_OPERACAO             = IOP.COD_OPERACAO ";

     public List<ItemOperacaoDTO> listarOperacoesPorCategoria(String nomeCategoria, String tipoOperacao) {
        return jdbcTemplate.query(
                SELECT_ITEM_PRODUTO + "WHERE CT.NME_CATEGORIA LIKE ? AND OP.TPO_OPERACAO = ?"
                , (resultSet, rowNum) ->
                        new ItemOperacaoDTO(
                                resultSet.getInt("COD_ITEM_OPERACAO"),
                                resultSet.getFloat("QTD_ITEM"),
                                resultSet.getDouble("VLR_ITEM"),
                                new Operacao(
                                        //OP.COD_OPERACAO, OP.DTA_OPERACAO, OP.VLR_TOTAL, OP.TPO_STATUS"+
                                    resultSet.getInt("COD_OPERACAO"),
                                    resultSet.getObject("DTA_OPERACAO", LocalDateTime.class),
                                    resultSet.getString("COD_NOTA_FISCAL"),
                                    resultSet.getFloat("VLR_TOTAL"),
                                    resultSet.getString("TPO_STATUS")
                                ),
                                new Produto(
                                        resultSet.getInt("COD_PRODUTO"),
                                        resultSet.getString("NME_PRODUTO"),
                                        new SubCategoria(
                                                resultSet.getInt("COD_SUBCATEGORIA"),
                                                resultSet.getString("NME_SUBCATEGORIA"),
                                                new Categoria(
                                                        resultSet.getInt("COD_CATEGORIA"),
                                                        resultSet.getString("NME_CATEGORIA")
                                                )
                                        ),
                                        new UnidadeMedida(
                                                resultSet.getInt("COD_UNIDADE_MEDIDA"),
                                                resultSet.getString("NME_UNIDADE_MEDIDA")
                                        )
                                )
                        ),
                new Object[]{"%"+nomeCategoria+"%", tipoOperacao}
        );
    }

    public List<ItemOperacaoDTO> listarItensOperacaoPorProduto(String nomeProduto, String tipoOperacao) {
        return jdbcTemplate.query(
                    SELECT_ITEM_PRODUTO + "WHERE PR.NME_PRODUTO LIKE ? AND OP.TPO_OPERACAO = ?"
                , (resultSet, rowNum) ->
                        new ItemOperacaoDTO(
                                resultSet.getInt("COD_ITEM_OPERACAO"),
                                resultSet.getFloat("QTD_ITEM"),
                                resultSet.getDouble("VLR_ITEM"),
                                new Operacao(
                                        resultSet.getInt("COD_OPERACAO"),
                                        resultSet.getObject("DTA_OPERACAO", LocalDateTime.class),
                                        resultSet.getString("COD_NOTA_FISCAL"),
                                        resultSet.getFloat("VLR_TOTAL"),
                                        resultSet.getString("TPO_STATUS")
                                ),
                                new Produto(
                                        resultSet.getInt("COD_PRODUTO"),
                                        resultSet.getString("NME_PRODUTO"),
                                        new SubCategoria(
                                                resultSet.getInt("COD_SUBCATEGORIA"),
                                                resultSet.getString("NME_SUBCATEGORIA"),
                                                new Categoria(
                                                        resultSet.getInt("COD_CATEGORIA"),
                                                        resultSet.getString("NME_CATEGORIA")
                                                )
                                        ),
                                        new UnidadeMedida(
                                                resultSet.getInt("COD_UNIDADE_MEDIDA"),
                                                resultSet.getString("NME_UNIDADE_MEDIDA")
                                        )
                                )
                        ),
                new Object[]{"%"+nomeProduto+"%", tipoOperacao}
        );
    }

    public List<ItemOperacaoDTO> listarItensPorOperacao(int idOperacao) {
        return jdbcTemplate.query(//SELECT_ITEM_PRODUTO + "  WHERE IOP.COD_OPERACAO = "+ idOperacao
                SELECT_ITEM_PRODUTO + "  WHERE OP.COD_OPERACAO = ? "
                , (resultSet, rowNum) ->
                        new ItemOperacaoDTO(
                                resultSet.getInt("COD_ITEM_OPERACAO"),
                                resultSet.getFloat("QTD_ITEM"),
                                resultSet.getDouble("VLR_ITEM"),
                                new Operacao(
                                        //OP.COD_OPERACAO, OP.DTA_OPERACAO, OP.VLR_TOTAL, OP.TPO_STATUS"+
                                        resultSet.getInt("COD_OPERACAO"),
                                        resultSet.getObject("DTA_OPERACAO", LocalDateTime.class),
                                        resultSet.getString("COD_NOTA_FISCAL"),
                                        resultSet.getFloat("VLR_TOTAL"),
                                        resultSet.getString("TPO_STATUS")
                                ),
                                new Produto(
                                        resultSet.getInt("COD_PRODUTO"),
                                        resultSet.getString("NME_PRODUTO"),
                                        new SubCategoria(
                                                resultSet.getInt("COD_SUBCATEGORIA"),
                                                resultSet.getString("NME_SUBCATEGORIA"),
                                                new Categoria(
                                                        resultSet.getInt("COD_CATEGORIA"),
                                                        resultSet.getString("NME_CATEGORIA")
                                                )
                                        ),
                                        new UnidadeMedida(
                                                resultSet.getInt("COD_UNIDADE_MEDIDA"),
                                                resultSet.getString("NME_UNIDADE_MEDIDA")
                                        )
                                )
                        ),
                new Object[]{idOperacao}
        );
    }
    public List<ItemOperacaoDTO> listarItensOperacaoPorTipoOperacao(String tipoOperacao) {
        return jdbcTemplate.query(
                SELECT_ITEM_PRODUTO + "  WHERE OP.TPO_OPERACAO = ? "
                , (resultSet, rowNum) ->
                        new ItemOperacaoDTO(
                                resultSet.getInt("COD_ITEM_OPERACAO"),
                                resultSet.getFloat("QTD_ITEM"),
                                resultSet.getDouble("VLR_ITEM"),
                                new Operacao(
                                        //OP.COD_OPERACAO, OP.DTA_OPERACAO, OP.VLR_TOTAL, OP.TPO_STATUS"+
                                        resultSet.getInt("COD_OPERACAO"),
                                        resultSet.getObject("DTA_OPERACAO", LocalDateTime.class),
                                        resultSet.getString("COD_NOTA_FISCAL"),
                                        resultSet.getFloat("VLR_TOTAL"),
                                        resultSet.getString("TPO_STATUS")
                                ),
                                new Produto(
                                        resultSet.getInt("COD_PRODUTO"),
                                        resultSet.getString("NME_PRODUTO"),
                                        new SubCategoria(
                                                resultSet.getInt("COD_SUBCATEGORIA"),
                                                resultSet.getString("NME_SUBCATEGORIA"),
                                                new Categoria(
                                                        resultSet.getInt("COD_CATEGORIA"),
                                                        resultSet.getString("NME_CATEGORIA")
                                                )
                                        ),
                                        new UnidadeMedida(
                                                resultSet.getInt("COD_UNIDADE_MEDIDA"),
                                                resultSet.getString("NME_UNIDADE_MEDIDA")
                                        )
                                )
                        ),
                new Object[]{tipoOperacao}
        );
    }

    public void incluirItemOperacao(ItemOperacao itemOperacao) throws  DataIntegrityViolationException{
        jdbcTemplate.update("INSERT INTO ITEM_OPERACAO (QTD_ITEM, VLR_ITEM, COD_OPERACAO, COD_PRODUTO) " +
                    " VALUES (?, ?, ?, ?)",
            itemOperacao.getQuantidadeItem(),
            itemOperacao.getValorItem(),
            itemOperacao.getCodOperacao(),
            itemOperacao.getCodProduto()
        );
    }

    public void removerItemOperacao(ItemOperacao itemOperacao) throws DataIntegrityViolationException {
        jdbcTemplate.update("DELETE FROM ITEM_OPERACAO WHERE COD_ITEM_OPERACAO = ?",
                itemOperacao.getCodItemOperacao()
        );
    }
}

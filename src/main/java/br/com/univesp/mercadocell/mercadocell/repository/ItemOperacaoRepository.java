package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.ItemOperacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ItemOperacaoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_ITEM_PRODUTO ="SELECT PR.COD_PRODUTO, PR.NME_PRODUTO, PR.DSC_PRODUTO "+
            ", SC.COD_SUBCATEGORIA, SC.NME_SUBCATEGORIA "+
            ", CT.COD_CATEGORIA, CT.NME_CATEGORIA "+
            ", UM.COD_UNIDADE_MEDIDA, UM.NME_UNIDADE_MEDIDA, UM.SGL_UNIDADE_MEDIDA "+
            ", IOP.COD_ITEM_OPERACAO,IOP.QTD_ITEM, IOP.VLR_ITEM,IOP.COD_OPERACAO " +
            " FROM PRODUTO PR "+
            " INNER JOIN SUBCATEGORIA 	    SC ON PR.COD_SUBCATEGORIA 	=	SC.COD_SUBCATEGORIA "+
            " INNER JOIN CATEGORIA 		    CT ON CT.COD_CATEGORIA 		= 	SC.COD_CATEGORIA "+
            " INNER JOIN UNIDADE_MEDIDA 	UM ON UM.COD_UNIDADE_MEDIDA = 	PR.COD_UNIDADE_MEDIDA "+
            " ITEM_OPERACAO IOP             ON  Pr.COD_PRODUTO             = IOP.COD_PRODUTO";

    public List<ItemOperacao> listarOperacoesPorCategoria(String nomeCategoria, String tipoOperacao) {
        return jdbcTemplate.query(SELECT_ITEM_PRODUTO + "  WHERE CT.NME_CATEGORIA = "+ nomeCategoria+
                        " AND IOP.TPO_OPERACAO = "+ tipoOperacao +"  "
                , (rs, rowNum) ->
                        new ItemOperacao(
                                rs.getInt("COD_ITEM_OPERACAO"),
                                rs.getFloat("QTD_ITEM"),
                                rs.getDouble("VLR_ITEM"),
                                rs.getInt("COD_OPERACAO"),
                                rs.getInt("COD_PRODUTO")
                        )
        );
    }

    public List<ItemOperacao> listarItensOperacaoPorProduto(String nomeProduto, String tipoOperacao) {
        return jdbcTemplate.query(SELECT_ITEM_PRODUTO + "  WHERE PR.NME_PRODUTO = "+ nomeProduto+
                        " AND IOP.TPO_OPERACAO = "+ tipoOperacao +"  "
                , (rs, rowNum) ->
                        new ItemOperacao(
                                rs.getInt("COD_ITEM_OPERACAO"),
                                rs.getFloat("QTD_ITEM"),
                                rs.getDouble("VLR_ITEM"),
                                rs.getInt("COD_OPERACAO"),
                                rs.getInt("COD_PRODUTO")
                        )
        );
    }

    public List<ItemOperacao> listarItensPorOperacao(int idOperacao) {
        return jdbcTemplate.query(SELECT_ITEM_PRODUTO + "  WHERE IOP.COD_OPERACAO = "+ idOperacao
                , (rs, rowNum) ->
                        new ItemOperacao(
                                rs.getInt("COD_ITEM_OPERACAO"),
                                rs.getFloat("QTD_ITEM"),
                                rs.getDouble("VLR_ITEM"),
                                rs.getInt("COD_OPERACAO"),
                                rs.getInt("COD_PRODUTO")
                        )
        );
    }

    public void incluirItensOperacao(List<ItemOperacao> listaItensOperacao) {
        for(ItemOperacao item : listaItensOperacao){
            jdbcTemplate.update("INSERT INTO ITEM_OPERACAO (QTD_ITEM, VLR_ITEM, COD_OPERACAO, COD_PRODUTO) "+
                            " VALUES (?, ?, ?, ?)",
                        item.getQuantidadeItem(),
                        item.getValorItem(),
                        item.getCodOperacao(),
                        item.getCodProduto()
                    );
        }
    }
}

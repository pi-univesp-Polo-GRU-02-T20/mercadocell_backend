package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Operacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OperacaoItemProdutoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_OPERACAO_PRODUTO =
            "SELECT COD_OPERACAO,DTA_OPERACAO,COD_NOTA_FISCAL, VLR_TOTAL,QTD_PARCELA," +
            "TPO_STATUS,COD_PESSOA,FLG_PAGO,TPO_OPERACAO" +
            " FROM `OPERACAO` O INNER JOIN PRODUTO P ON " +
            " P.COD_PRODUTO = O.COD_PRODUTO";

    public List<Operacao> listarOperacoesPorCategoria(String nomeCategoria, String tipoOperacao) {
        try {
            return jdbcTemplate.query(
                    SELECT_OPERACAO_PRODUTO + "  WHERE `COD_PESSOA` = ? and TPO_OPERACAO = ?",
                    (resultSet, rowNum) ->
                            new Operacao(
                                    resultSet.getInt("COD_OPERACAO"),
                                    resultSet.getObject("DTA_OPERACAO", LocalDateTime.class),
                                    resultSet.getString("COD_NOTA_FISCAL"),
                                    resultSet.getFloat("VLR_TOTAL"),
                                    resultSet.getInt("QTD_PARCELA"),
                                    resultSet.getBoolean("FLG_PAGO"),
                                    resultSet.getString("TPO_OPERACAO"), // C - Compra; V -- Venda
                                    resultSet.getString("TPO_STATUS"), // P - Pedido; O- orçamento
                                    resultSet.getInt("COD_PESSOA")
                            ),
                    new Object[]{ nomeCategoria, tipoOperacao}
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Operacao> listarOperacoesPorProduto(String nomeProduto, String tipoOperacao) {
        try {
            return jdbcTemplate.query(
                    SELECT_OPERACAO_PRODUTO + "  WHERE `COD_PESSOA` = ? and TPO_OPERACAO = ?",
                    (resultSet, rowNum) ->
                            new Operacao(
                                    resultSet.getInt("COD_OPERACAO"),
                                    resultSet.getObject("DTA_OPERACAO", LocalDateTime.class),
                                    resultSet.getString("COD_NOTA_FISCAL"),
                                    resultSet.getFloat("VLR_TOTAL"),
                                    resultSet.getInt("QTD_PARCELA"),
                                    resultSet.getBoolean("FLG_PAGO"),
                                    resultSet.getString("TPO_OPERACAO"), // C - Compra; V -- Venda
                                    resultSet.getString("TPO_STATUS"), // P - Pedido; O- orçamento
                                    resultSet.getInt("COD_PESSOA")
                            ),
                    new Object[]{ nomeProduto, tipoOperacao}
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
}

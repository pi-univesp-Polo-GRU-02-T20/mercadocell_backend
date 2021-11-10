package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PagamentoOperacaoRespository {

    private String SELECT_PAGAMENTO_OPERACAO =
    " SELECT PO.COD_PAGAMENTO, PO.VLR_PAGAMENTO, PO.DTA_PAGAMENTO, PO.DTA_VENCIMENTO,"+
    " OP.COD_OPERACAO,  OP.DTA_OPERACAO, OP.COD_NOTA_FISCAL, OP.VLR_TOTAL,"+
    " OP.QTD_PARCELA, OP.TPO_OPERACAO, OP.FLG_PAGO,"+
    " TP.COD_TIPO_PAGAMENTO, TP.NME_TIPO_PAGAMENTO "+
    " FROM  OPERACAO OP"+
    " INNER JOIN TIPO_PAGAMENTO TP " +
    " ON TP.COD_TIPO_PAGAMENTO = OP.COD_TIPO_PAGAMENTO" +
    " INNER JOIN  PAGAMENTO_OPERACAO PO" +
    " ON PO.COD_OPERACAO =  OP.COD_OPERACAO ";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void incluirPagamentoOperacao(PagamentoOperacao pagamentoOperacao) throws DataIntegrityViolationException {
        jdbcTemplate.update(
                "INSERT INTO PAGAMENTO_OPERACAO (VLR_PAGAMENTO, DTA_PAGAMENTO, DTA_VENCIMENTO, COD_OPERACAO)" +
                    " VALUES (?,?,?,?)",
                pagamentoOperacao.getValorPagamento(),
                pagamentoOperacao.getDataPagamento().toString(),
                pagamentoOperacao.getDataVencimento().toString(),
                pagamentoOperacao.getOperacao().getCodOperacao()
        );
    }

    public void removerPagamentoOperacao(Integer idPagamento) {
        jdbcTemplate.update("DELETE FROM PAGAMENTO_OPERACAO WHERE COD_PAGAMENTO = ?",
                idPagamento
        );
    }

    public List<PagamentoOperacao> listarPagamentoPorTipoOperacao(String tipoOperacao) {
        return jdbcTemplate.query(
                SELECT_PAGAMENTO_OPERACAO + "  WHERE TPO_OPERACAO = ?",
                (resultSet, rowNum) ->
                        new PagamentoOperacao(
                                resultSet.getInt("COD_PAGAMENTO"),
                                resultSet.getDouble("VLR_PAGAMENTO"),
                                resultSet.getObject("DTA_PAGAMENTO", LocalDateTime.class),
                                resultSet.getObject("DTA_VENCIMENTO", LocalDateTime.class),
                                new Operacao(
                                        resultSet.getInt("COD_OPERACAO"),
                                        resultSet.getObject("DTA_OPERACAO", LocalDateTime.class),
                                        resultSet.getString("COD_NOTA_FISCAL"),
                                        resultSet.getDouble("VLR_TOTAL"),
                                        resultSet.getInt("QTD_PARCELA"),
                                        resultSet.getBoolean("FLG_PAGO"),
                                        resultSet.getString("TPO_OPERACAO"), // C - Compra; V -- Venda
                                        new TipoPagamento(
                                                resultSet.getInt("COD_TIPO_PAGAMENTO"),
                                                resultSet.getString("NME_TIPO_PAGAMENTO")
                                        )
                                )
                        ),
                new Object[]{ tipoOperacao}
        );
    }

    public List<PagamentoOperacao> buscarPagamentoPorOperacaoId(Integer idOperacao) {
        return jdbcTemplate.query(
                SELECT_PAGAMENTO_OPERACAO + "  WHERE OP.COD_OPERACAO = ? ",
                (resultSet, rowNum) ->
                        new PagamentoOperacao(
                                resultSet.getInt("COD_PAGAMENTO"),
                                resultSet.getDouble("VLR_PAGAMENTO"),
                                resultSet.getObject("DTA_PAGAMENTO", LocalDateTime.class),
                                resultSet.getObject("DTA_VENCIMENTO", LocalDateTime.class),
                                new Operacao(
                                        resultSet.getInt("COD_OPERACAO"),
                                        resultSet.getObject("DTA_OPERACAO", LocalDateTime.class),
                                        resultSet.getString("COD_NOTA_FISCAL"),
                                        resultSet.getDouble("VLR_TOTAL"),
                                        resultSet.getInt("QTD_PARCELA"),
                                        resultSet.getBoolean("FLG_PAGO"),
                                        resultSet.getString("TPO_OPERACAO"), // C - Compra; V -- Venda
                                        new TipoPagamento(
                                                resultSet.getInt("COD_TIPO_PAGAMENTO"),
                                                resultSet.getString("NME_TIPO_PAGAMENTO")
                                        )
                                )
                        ),
                new Object[]{idOperacao}
        );
    }

    public List<PagamentoOperacao> listarPagamentoPorTipoOperacao(String tipoOperacao, Boolean pago)
            throws DataIntegrityViolationException{
        return jdbcTemplate.query(
                SELECT_PAGAMENTO_OPERACAO + "  WHERE OP.TPO_OPERACAO = ? AND OP.FLG_STATUS = ? ",
                (resultSet, rowNum) ->
                        new PagamentoOperacao(
                                resultSet.getInt("COD_PAGAMENTO"),
                                resultSet.getDouble("VLR_PAGAMENTO"),
                                resultSet.getObject("DTA_PAGAMENTO", LocalDateTime.class),
                                resultSet.getObject("DTA_VENCIMENTO", LocalDateTime.class),
                                new Operacao(
                                        resultSet.getInt("COD_OPERACAO"),
                                        resultSet.getObject("DTA_OPERACAO", LocalDateTime.class),
                                        resultSet.getString("COD_NOTA_FISCAL"),
                                        resultSet.getDouble("VLR_TOTAL"),
                                        resultSet.getInt("QTD_PARCELA"),
                                        resultSet.getBoolean("FLG_PAGO"),
                                        resultSet.getString("TPO_OPERACAO"), // C - Compra; V -- Venda
                                        new TipoPagamento(
                                                resultSet.getInt("COD_TIPO_PAGAMENTO"),
                                                resultSet.getString("NME_TIPO_PAGAMENTO")
                                        )
                                )
                        ),
                new Object[]{ tipoOperacao, pago}
        );
    }
}

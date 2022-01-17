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

    private static final String SELECT_PAGAMENTO_OPERACAO =
            " SELECT PO.COD_PAGAMENTO, PO.VLR_PAGAMENTO, PO.DTA_PAGAMENTO, PO.DTA_VENCIMENTO,"+
                    " OP.COD_OPERACAO,  OP.DTA_OPERACAO, OP.COD_NOTA_FISCAL, OP.VLR_TOTAL,"+
                    " OP.QTD_PARCELA, OP.TPO_OPERACAO, OP.FLG_PAGO,"+
                    " TP.COD_TIPO_PAGAMENTO, TP.NME_TIPO_PAGAMENTO "+
                    " FROM  OPERACAO OP"+
                    " INNER JOIN TIPO_PAGAMENTO TP " +
                    " ON TP.COD_TIPO_PAGAMENTO = OP.COD_TIPO_PAGAMENTO" +
                    " INNER JOIN  PAGAMENTO_OPERACAO PO" +
                    " ON PO.COD_OPERACAO =  OP.COD_OPERACAO ";
    private static final String COL_VLR_PAGAMENTO       = "VLR_PAGAMENTO";
    private static final String COL_COD_PAGAMENTO       = "COD_PAGAMENTO";
    private static final String COL_DTA_PAGAMENTO		= "DTA_PAGAMENTO";
    private static final String COL_DTA_VENCIMENTO		= "DTA_VENCIMENTO";
    private static final String COL_COD_OPERACAO		= "COD_OPERACAO";
    private static final String COL_DTA_OPERACAO		= "DTA_OPERACAO";
    private static final String COL_COD_NOTA_FISCAL		= "COD_NOTA_FISCAL";
    private static final String COL_VLR_TOTAL			= "VLR_TOTAL";
    private static final String COL_QTD_PARCELA			= "QTD_PARCELA";
    private static final String COL_FLG_PAGO			= "FLG_PAGO";
    private static final String COL_TPO_OPERACAO		= "TPO_OPERACAO";
    private static final String COL_COD_TIPO_PAGAMENTO	= "COD_TIPO_PAGAMENTO";
    private static final String COL_NME_TIPO_PAGAMENTO	= "NME_TIPO_PAGAMENTO";

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
                                resultSet.getInt(COL_COD_PAGAMENTO),
                                resultSet.getDouble(COL_VLR_PAGAMENTO),
                                resultSet.getObject(COL_DTA_PAGAMENTO, LocalDateTime.class),
                                resultSet.getObject(COL_DTA_VENCIMENTO, LocalDateTime.class),
                                new Operacao(
                                        resultSet.getInt(COL_COD_OPERACAO),
                                        resultSet.getObject(COL_DTA_OPERACAO, LocalDateTime.class),
                                        resultSet.getString(COL_COD_NOTA_FISCAL),
                                        resultSet.getDouble(COL_VLR_TOTAL),
                                        resultSet.getInt(COL_QTD_PARCELA),
                                        resultSet.getBoolean(COL_FLG_PAGO),
                                        resultSet.getString(COL_TPO_OPERACAO), // C - Compra; V -- Venda
                                        new TipoPagamento(
                                                resultSet.getInt(COL_COD_TIPO_PAGAMENTO),
                                                resultSet.getString(COL_NME_TIPO_PAGAMENTO)
                                        )
                                )
                        ),
                tipoOperacao
        );
    }

    public List<PagamentoOperacao> buscarPagamentoPorOperacaoId(Integer idOperacao) {
        return jdbcTemplate.query(
                SELECT_PAGAMENTO_OPERACAO + "  WHERE OP.COD_OPERACAO = ? ",
                (resultSet, rowNum) ->
                        new PagamentoOperacao(
                                resultSet.getInt(COL_COD_PAGAMENTO),
                                resultSet.getDouble(COL_VLR_PAGAMENTO),
                                resultSet.getObject(COL_DTA_PAGAMENTO, LocalDateTime.class),
                                resultSet.getObject(COL_DTA_VENCIMENTO, LocalDateTime.class),
                                new Operacao(
                                        resultSet.getInt(COL_COD_OPERACAO),
                                        resultSet.getObject(COL_DTA_OPERACAO, LocalDateTime.class),
                                        resultSet.getString(COL_COD_NOTA_FISCAL),
                                        resultSet.getDouble(COL_VLR_TOTAL),
                                        resultSet.getInt(COL_QTD_PARCELA),
                                        resultSet.getBoolean(COL_FLG_PAGO),
                                        resultSet.getString(COL_TPO_OPERACAO), // C - Compra; V -- Venda
                                        new TipoPagamento(
                                                resultSet.getInt(COL_COD_TIPO_PAGAMENTO),
                                                resultSet.getString(COL_NME_TIPO_PAGAMENTO)
                                        )
                                )
                        ),
                idOperacao
        );
    }

    public List<PagamentoOperacao> listarPagamentoPorTipoOperacao(String tipoOperacao, Boolean pago)
            throws DataIntegrityViolationException{
        return jdbcTemplate.query(
                SELECT_PAGAMENTO_OPERACAO + "  WHERE OP.TPO_OPERACAO = ? AND OP.FLG_STATUS = ? ",
                (resultSet, rowNum) ->
                        new PagamentoOperacao(
                                resultSet.getInt(COL_COD_PAGAMENTO),
                                resultSet.getDouble(COL_VLR_PAGAMENTO),
                                resultSet.getObject(COL_DTA_PAGAMENTO, LocalDateTime.class),
                                resultSet.getObject(COL_DTA_VENCIMENTO, LocalDateTime.class),
                                new Operacao(
                                        resultSet.getInt(COL_COD_OPERACAO),
                                        resultSet.getObject(COL_DTA_OPERACAO, LocalDateTime.class),
                                        resultSet.getString(COL_COD_NOTA_FISCAL),
                                        resultSet.getDouble(COL_VLR_TOTAL),
                                        resultSet.getInt(COL_QTD_PARCELA),
                                        resultSet.getBoolean(COL_FLG_PAGO),
                                        resultSet.getString(COL_TPO_OPERACAO), // C - Compra; V -- Venda
                                        new TipoPagamento(
                                                resultSet.getInt(COL_COD_TIPO_PAGAMENTO),
                                                resultSet.getString(COL_NME_TIPO_PAGAMENTO)
                                        )
                                )
                        ),
                tipoOperacao, pago
        );
    }
}

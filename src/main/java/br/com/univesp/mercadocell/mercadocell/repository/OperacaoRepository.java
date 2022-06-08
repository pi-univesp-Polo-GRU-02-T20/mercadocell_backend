package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Operacao;
import br.com.univesp.mercadocell.mercadocell.model.Pessoa;
import br.com.univesp.mercadocell.mercadocell.model.TipoPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OperacaoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String SELECT_OPERACAO =
            "SELECT OP.COD_OPERACAO,OP.DTA_OPERACAO,OP.COD_NOTA_FISCAL,OP.VLR_TOTAL,OP.QTD_PARCELA," +
            " OP.TPO_STATUS,OP.FLG_PAGO,OP.TPO_OPERACAO, P.COD_PESSOA, P.NME_PESSOA, " +
            " TP.COD_TIPO_PAGAMENTO, TP.NME_TIPO_PAGAMENTO "+
            " FROM OPERACAO OP INNER JOIN PESSOA P ON " +
            " OP.COD_PESSOA = P.COD_PESSOA " +
            " INNER JOIN TIPO_PAGAMENTO TP ON " +
            " TP.COD_TIPO_PAGAMENTO = OP.COD_TIPO_PAGAMENTO"
            ;
    private static final String FILTRO_RANGE_DATA_OPERACAO = " WHERE DATE_FORMAT(DTA_OPERACAO,'%Y-%m-%d') >= ? AND " +
            " DATE_FORMAT(DTA_OPERACAO,'%Y-%m-%d') <= ?" +
            " AND TPO_OPERACAO = ?";

    private static final String COL_COD_OPERACAO = "COD_OPERACAO";
    private static final String COL_DTA_OPERACAO = "DTA_OPERACAO";
    private static final String COL_COD_NOTA_FISCAL = "COD_NOTA_FISCAL";
    private static final String COL_VLR_TOTAL = "VLR_TOTAL";
    private static final String COL_QTD_PARCELA = "QTD_PARCELA";
    private static final String COL_FLG_PAGO = "FLG_PAGO";
    private static final String COL_TPO_OPERACAO = "TPO_OPERACAO";
    private static final String COL_TPO_STATUS = "TPO_STATUS";
    private static final String COL_COD_PESSOA = "COD_PESSOA";
    private static final String COL_NME_PESSOA = "NME_PESSOA";
    private static final String COL_COD_TIPO_PAGAMENTO = "COD_TIPO_PAGAMENTO";
    private static final String COL_NME_TIPO_PAGAMENTO = "NME_TIPO_PAGAMENTO";

    public void cadastrarOperacao(Operacao operacao) {
        jdbcTemplate.update(
                "INSERT INTO OPERACAO " +
                    "(DTA_OPERACAO, COD_NOTA_FISCAL, VLR_TOTAL, QTD_PARCELA, TPO_STATUS," +
                        " COD_PESSOA, FLG_PAGO, TPO_OPERACAO, COD_TIPO_PAGAMENTO)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )",
                    operacao.getDataOperacao().toString(),
                    operacao.getCodNotaFiscal(),
                    operacao.getValorTotal(),
                    operacao.getQuantidadeParcela(),
                    operacao.getTipoStatusOperacao(),
                    operacao.getPessoa().getCodPessoa(),
                    operacao.getPago(),
                    operacao.getTipoOperacao(),
                    operacao.getTipoPagamento().getCodTipoPagamento()
                );
    }

    public Operacao buscarOperacaoPorId(int idOperacao) {
            return jdbcTemplate.queryForObject(
                    SELECT_OPERACAO + "  WHERE COD_OPERACAO = ?",
                     (resultSet, rowNum) ->
                            new Operacao(
                                    resultSet.getInt(COL_COD_OPERACAO),
                                    resultSet.getObject(COL_DTA_OPERACAO, LocalDateTime.class),
                                    resultSet.getString(COL_COD_NOTA_FISCAL),
                                    resultSet.getDouble(COL_VLR_TOTAL),
                                    resultSet.getInt(COL_QTD_PARCELA),
                                    resultSet.getBoolean(COL_FLG_PAGO),
                                    resultSet.getString(COL_TPO_OPERACAO), // C - Compra; V -- Venda
                                    resultSet.getString(COL_TPO_STATUS), // P - Pedido; O- orçamento
                                    new Pessoa(
                                            resultSet.getInt(COL_COD_PESSOA),
                                            resultSet.getString(COL_NME_PESSOA)
                                    ),
                                    new TipoPagamento(
                                            resultSet.getInt(COL_COD_TIPO_PAGAMENTO),
                                            resultSet.getString(COL_NME_TIPO_PAGAMENTO)
                                    )
                            ),
                    idOperacao
            );
    }

    public List<Operacao> listarOperacoesPorPessoa(int codPessoa, String tipoOperacao) throws EmptyResultDataAccessException{
            return jdbcTemplate.query(
                    SELECT_OPERACAO + "  WHERE P.COD_PESSOA = ? and TPO_OPERACAO = ?",
                    (resultSet, rowNum) ->
                            new Operacao(
                                    resultSet.getInt(COL_COD_OPERACAO),
                                    resultSet.getObject(COL_DTA_OPERACAO, LocalDateTime.class),
                                    resultSet.getString(COL_COD_NOTA_FISCAL),
                                    resultSet.getDouble(COL_VLR_TOTAL),
                                    resultSet.getInt(COL_QTD_PARCELA),
                                    resultSet.getBoolean(COL_FLG_PAGO),
                                    resultSet.getString(COL_TPO_OPERACAO), // C - Compra; V -- Venda
                                    resultSet.getString(COL_TPO_STATUS), // P - Pedido; O- orçamento
                                    new Pessoa(
                                            resultSet.getInt(COL_COD_PESSOA),
                                            resultSet.getString(COL_NME_PESSOA)
                                    ),
                                    new TipoPagamento(
                                            resultSet.getInt(COL_COD_TIPO_PAGAMENTO),
                                            resultSet.getString(COL_NME_TIPO_PAGAMENTO)
                                    )
                            ),
                    codPessoa, tipoOperacao
            );
    }

    public List<Operacao> listarOperacoesPagas(boolean pago, String tipoOperacao) {
            return jdbcTemplate.query(
                    SELECT_OPERACAO + "  WHERE FLG_PAGO = ? AND  TPO_OPERACAO = ?",
                    (resultSet, rowNum) ->
                            new Operacao(
                                    resultSet.getInt(COL_COD_OPERACAO),
                                    resultSet.getObject(COL_DTA_OPERACAO, LocalDateTime.class),
                                    resultSet.getString(COL_COD_NOTA_FISCAL),
                                    resultSet.getDouble(COL_VLR_TOTAL),
                                    resultSet.getInt(COL_QTD_PARCELA),
                                    resultSet.getBoolean(COL_FLG_PAGO),
                                    resultSet.getString(COL_TPO_OPERACAO), // C - Compra; V -- Venda
                                    resultSet.getString(COL_TPO_STATUS), // P - Pedido; O- orçamento
                                    new Pessoa(
                                            resultSet.getInt(COL_COD_PESSOA),
                                            resultSet.getString(COL_NME_PESSOA)
                                    ),
                                    new TipoPagamento(
                                            resultSet.getInt(COL_COD_TIPO_PAGAMENTO),
                                            resultSet.getString(COL_NME_TIPO_PAGAMENTO)
                                    )
                            ),
                pago, tipoOperacao
            );
    }

    public List<Operacao> listarOperacoesPorPeriodo(LocalDate dataInicio, LocalDate dataTermino, String tipoOperacao) {
            return jdbcTemplate.query(
                    SELECT_OPERACAO + FILTRO_RANGE_DATA_OPERACAO,
                    (resultSet, rowNum) ->
                            new Operacao(
                                    resultSet.getInt(COL_COD_OPERACAO),
                                    resultSet.getObject(COL_DTA_OPERACAO, LocalDateTime.class),
                                    resultSet.getString(COL_COD_NOTA_FISCAL),
                                    resultSet.getDouble(COL_VLR_TOTAL),
                                    resultSet.getInt(COL_QTD_PARCELA),
                                    resultSet.getBoolean(COL_FLG_PAGO),
                                    resultSet.getString(COL_TPO_OPERACAO), // C - Compra; V -- Venda
                                    resultSet.getString(COL_TPO_STATUS), // P - Pedido; O- orçamento
                                    new Pessoa(
                                            resultSet.getInt(COL_COD_PESSOA),
                                            resultSet.getString(COL_NME_PESSOA)
                                    ),
                                    new TipoPagamento(
                                            resultSet.getInt(COL_COD_TIPO_PAGAMENTO),
                                            resultSet.getString(COL_NME_TIPO_PAGAMENTO)
                                    )
                            ),
                    dataInicio, dataTermino, tipoOperacao
            );
    }


    public List<Operacao> listarOperacoes() {
        return jdbcTemplate.query(
                SELECT_OPERACAO,
                (resultSet, rowNum) ->
                        new Operacao(
                                resultSet.getInt(COL_COD_OPERACAO),
                                resultSet.getObject(COL_DTA_OPERACAO, LocalDateTime.class),
                                resultSet.getString(COL_COD_NOTA_FISCAL),
                                resultSet.getDouble(COL_VLR_TOTAL),
                                resultSet.getInt(COL_QTD_PARCELA),
                                resultSet.getBoolean(COL_FLG_PAGO),
                                resultSet.getString(COL_TPO_OPERACAO), // C - Compra; V -- Venda
                                resultSet.getString(COL_TPO_STATUS), // P - Pedido; O- orçamento
                                new Pessoa(
                                        resultSet.getInt(COL_COD_PESSOA),
                                        resultSet.getString(COL_NME_PESSOA)
                                ),
                                new TipoPagamento(
                                        resultSet.getInt(COL_COD_TIPO_PAGAMENTO),
                                        resultSet.getString(COL_NME_TIPO_PAGAMENTO)
                                )
                        )
        );
    }

    public void atualizarOperacao(Operacao operacao) throws DataIntegrityViolationException{
        jdbcTemplate.update(
                "UPDATE Operacao SET DTA_OPERACAO = ?, COD_NOTA_FISCAL = ?, VLR_TOTAL = ?, QTD_PARCELA = ?, " +
                        " TPO_STATUS = ?, COD_PESSOA = ?, FLG_PAGO = ?, TPO_OPERACAO = ? "+
                        " WHERE COD_OPERACAO = ?",
                operacao.getDataOperacao().toString(),
                operacao.getCodNotaFiscal(),
                operacao.getValorTotal(),
                operacao.getQuantidadeParcela(),
                operacao.getTipoStatusOperacao(),
                operacao.getPessoa().getCodPessoa(),
                operacao.getPago(),
                operacao.getTipoOperacao(),
                operacao.getCodOperacao()
        );
    }

    public void deletarOperacao(int codOperacao) throws DataIntegrityViolationException {
        jdbcTemplate.update(
                "DELETE FROM OPERACAO WHERE COD_OPERACAO = ?",
                codOperacao
        );
    }
}

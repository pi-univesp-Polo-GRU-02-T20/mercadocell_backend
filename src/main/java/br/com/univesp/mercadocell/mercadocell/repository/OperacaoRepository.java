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

    public void cadastrarOperacao(Operacao operacao) {
        jdbcTemplate.update(
                "INSERT INTO `OPERACAO` " +
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
                                    resultSet.getInt("COD_OPERACAO"),
                                    resultSet.getObject("DTA_OPERACAO", LocalDateTime.class),
                                    resultSet.getString("COD_NOTA_FISCAL"),
                                    resultSet.getDouble("VLR_TOTAL"),
                                    resultSet.getInt("QTD_PARCELA"),
                                    resultSet.getBoolean("FLG_PAGO"),
                                    resultSet.getString("TPO_OPERACAO"), // C - Compra; V -- Venda
                                    resultSet.getString("TPO_STATUS"), // P - Pedido; O- orçamento
                                    new Pessoa(
                                            resultSet.getInt("COD_PESSOA"),
                                            resultSet.getString("NME_PESSOA")
                                    ),
                                    new TipoPagamento(
                                            resultSet.getInt("COD_TIPO_PAGAMENTO"),
                                            resultSet.getString("NME_TIPO_PAGAMENTO")
                                    )
                            ),
                    new Object[]{idOperacao}
            );
    }

    public List<Operacao> listarOperacoesPorPessoa(int codPessoa, String tipoOperacao) throws EmptyResultDataAccessException{
            return jdbcTemplate.query(
                    SELECT_OPERACAO + "  WHERE P.COD_PESSOA = ? and TPO_OPERACAO = ?",
                    (resultSet, rowNum) ->
                            new Operacao(
                                    resultSet.getInt("COD_OPERACAO"),
                                    resultSet.getObject("DTA_OPERACAO", LocalDateTime.class),
                                    resultSet.getString("COD_NOTA_FISCAL"),
                                    resultSet.getDouble("VLR_TOTAL"),
                                    resultSet.getInt("QTD_PARCELA"),
                                    resultSet.getBoolean("FLG_PAGO"),
                                    resultSet.getString("TPO_OPERACAO"), // C - Compra; V -- Venda
                                    resultSet.getString("TPO_STATUS"), // P - Pedido; O- orçamento
                                    new Pessoa(
                                            resultSet.getInt("COD_PESSOA"),
                                            resultSet.getString("NME_PESSOA")
                                    ),
                                    new TipoPagamento(
                                            resultSet.getInt("COD_TIPO_PAGAMENTO"),
                                            resultSet.getString("NME_TIPO_PAGAMENTO")
                                    )
                            ),
                    new Object[]{codPessoa, tipoOperacao}
            );
    }

    public List<Operacao> listarOperacoesPagas(boolean pago, String tipoOperacao) {
            return jdbcTemplate.query(
                    SELECT_OPERACAO + "  WHERE FLG_PAGO = ? AND  TPO_OPERACAO = ?",
                    (resultSet, rowNum) ->
                            new Operacao(
                                    resultSet.getInt("COD_OPERACAO"),
                                    resultSet.getObject("DTA_OPERACAO", LocalDateTime.class),
                                    resultSet.getString("COD_NOTA_FISCAL"),
                                    resultSet.getDouble("VLR_TOTAL"),
                                    resultSet.getInt("QTD_PARCELA"),
                                    resultSet.getBoolean("FLG_PAGO"),
                                    resultSet.getString("TPO_OPERACAO"), // C - Compra; V -- Venda
                                    resultSet.getString("TPO_STATUS"), // P - Pedido; O- orçamento
                                    new Pessoa(
                                            resultSet.getInt("COD_PESSOA"),
                                            resultSet.getString("NME_PESSOA")
                                    ),
                                    new TipoPagamento(
                                            resultSet.getInt("COD_TIPO_PAGAMENTO"),
                                            resultSet.getString("NME_TIPO_PAGAMENTO")
                                    )
                            ),
                new Object[]{pago, tipoOperacao}
            );
    }

    public List<Operacao> listarOperacoesPorPeriodo(LocalDate dataInicio, LocalDate dataTermino, String tipoOperacao) {
            return jdbcTemplate.query(
                    SELECT_OPERACAO + FILTRO_RANGE_DATA_OPERACAO,
                    (resultSet, rowNum) ->
                            new Operacao(
                                    resultSet.getInt("COD_OPERACAO"),
                                    resultSet.getObject("DTA_OPERACAO", LocalDateTime.class),
                                    resultSet.getString("COD_NOTA_FISCAL"),
                                    resultSet.getDouble("VLR_TOTAL"),
                                    resultSet.getInt("QTD_PARCELA"),
                                    resultSet.getBoolean("FLG_PAGO"),
                                    resultSet.getString("TPO_OPERACAO"), // C - Compra; V -- Venda
                                    resultSet.getString("TPO_STATUS"), // P - Pedido; O- orçamento
                                    new Pessoa(
                                            resultSet.getInt("COD_PESSOA"),
                                            resultSet.getString("NME_PESSOA")
                                    ),
                                    new TipoPagamento(
                                            resultSet.getInt("COD_TIPO_PAGAMENTO"),
                                            resultSet.getString("NME_TIPO_PAGAMENTO")
                                    )
                            ),
                    new Object[]{dataInicio, dataTermino, tipoOperacao}
            );
    }


    public List<Operacao> listarOperacoes() {
        return jdbcTemplate.query(
                SELECT_OPERACAO,
                (resultSet, rowNum) ->
                        new Operacao(
                                resultSet.getInt("COD_OPERACAO"),
                                resultSet.getObject("DTA_OPERACAO", LocalDateTime.class),
                                resultSet.getString("COD_NOTA_FISCAL"),
                                resultSet.getDouble("VLR_TOTAL"),
                                resultSet.getInt("QTD_PARCELA"),
                                resultSet.getBoolean("FLG_PAGO"),
                                resultSet.getString("TPO_OPERACAO"), // C - Compra; V -- Venda
                                resultSet.getString("TPO_STATUS"), // P - Pedido; O- orçamento
                                new Pessoa(
                                        resultSet.getInt("COD_PESSOA"),
                                        resultSet.getString("NME_PESSOA")
                                ),
                                new TipoPagamento(
                                        resultSet.getInt("COD_TIPO_PAGAMENTO"),
                                        resultSet.getString("NME_TIPO_PAGAMENTO")
                                )
                        )
        );
    }

    public void atualizarOperacao(Operacao operacao) throws DataIntegrityViolationException{
        jdbcTemplate.update(
                "UPDATE `Operacao` SET `DTA_OPERACAO` = ?, COD_NOTA_FISCAL = ?, VLR_TOTAL = ?, QTD_PARCELA = ?, " +
                        " TPO_STATUS = ?, COD_PESSOA = ?, FLG_PAGO = ?, TPO_OPERACAO = ? "+
                        " WHERE `COD_OPERACAO` = ?",
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
                "DELETE FROM `OPERACAO` WHERE `COD_OPERACAO` = ?",
                codOperacao
        );
    }
}

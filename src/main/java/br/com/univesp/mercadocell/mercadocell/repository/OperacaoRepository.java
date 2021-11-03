package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Operacao;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String SELECT_OPERACAO = "SELECT COD_OPERACAO,DTA_OPERACAO,COD_NOTA_FISCAL,VLR_TOTAL,QTD_PARCELA," +
            "TPO_STATUS,COD_PESSOA,FLG_PAGO,TPO_OPERACAO FROM `OPERACAO`";
    private static final String FILTRO_RANGE_DATA_OPERACAO = " WHERE DATE_FORMAT(DTA_OPERACAO,'%Y-%m-%d') >= ? AND " +
            " DATE_FORMAT(DTA_OPERACAO,'%Y-%m-%d') <= ?";

    public void cadastrarOperacao(Operacao operacao) {
        jdbcTemplate.update(
                "INSERT INTO `OPERACAO` " +
                    "(DTA_OPERACAO, COD_NOTA_FISCAL, VLR_TOTAL, QTD_PARCELA, TPO_STATUS," +
                        " COD_PESSOA, FLG_PAGO, TPO_OPERACAO)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ? )",
                    operacao.getDataOperacao().toString(),
                    operacao.getCodNotaFiscal(),
                    operacao.getValorTotal(),
                    operacao.getQuantidadeParcela(),
                    operacao.getTipoStatusOperacao(),
                    operacao.getCodPessoa(),
                    operacao.isPago(),
                    operacao.getTipoOperacao()
                );
    }

    public Operacao buscarOperacaoPorId(int idOperacao) {
        try {
            return jdbcTemplate.queryForObject(
                    SELECT_OPERACAO + "  WHERE `COD_OPERACAO` = ?",
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
                    new Object[]{idOperacao}
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Operacao> listarOperacoesPorPessoa(int codPessoa, String tipoOperacao) {
        try {
            return jdbcTemplate.query(
                    SELECT_OPERACAO + "  WHERE `COD_PESSOA` = ? and TPO_OPERACAO = ?",
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
                    new Object[]{codPessoa, tipoOperacao}
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Operacao> listarOperacoesPagas(boolean pago) {
        try {
            return jdbcTemplate.query(
                    SELECT_OPERACAO + "  WHERE `FLG_PAGO` = ?",
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
                    new Object[]{pago}
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    public List<Operacao> listarOperacoesPorPeriodo(LocalDate dataInicio, LocalDate dataTermino) {
        try {
            return jdbcTemplate.query(
                    SELECT_OPERACAO + FILTRO_RANGE_DATA_OPERACAO,
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
                    new Object[]{dataInicio, dataTermino}
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }


    public List<Operacao> listarOperacoes() {
        return jdbcTemplate.query(
                SELECT_OPERACAO,
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
                        )
        );
    }

    public void atualizarOperacao(Operacao operacao) {
        jdbcTemplate.update(
                //COD_OPERACAO,DTA_OPERACAO,COD_NOTA_FISCAL,VLR_TOTAL,QTD_PARCELA," +
                //"TPO_STATUS,COD_PESSOA,FLG_PAGO,TPO_OPERACAO FROM `OPERACAO
                "UPDATE `Operacao` SET `DTA_OPERACAO` = ?, COD_NOTA_FISCAL = ?, VLR_TOTAL = ?, QTD_PARCELA = ?, " +
                        " TPO_STATUS = ?, COD_PESSOA = ?, FLG_PAGO = ?, TPO_OPERACAO = ? "+
                        " WHERE `COD_OPERACAO` = ?",
                //"COD_OPERACAO,DTA_OPERACAO,COD_NOTA_FISCAL,VLR_TOTAL,QTD_PARCELA," +
                //"TPO_STATUS,COD_PESSOA,FLG_PAGO,TPO_OPERACAO FROM `OPERACAO
                operacao.getDataOperacao().toString(),
                operacao.getCodNotaFiscal(),
                operacao.getValorTotal(),
                operacao.getQuantidadeParcela(),
                operacao.getTipoStatusOperacao(),
                operacao.getCodPessoa(),
                operacao.isPago(),
                operacao.getTipoOperacao(),
                operacao.getCodOperacao()
        );
    }

    public void deletarOperacao(int codOperacao) {
        jdbcTemplate.update(
                "DELETE FROM `OPERACAO` WHERE `COD_OPERACAO` = ?",
                codOperacao
        );
    }




}

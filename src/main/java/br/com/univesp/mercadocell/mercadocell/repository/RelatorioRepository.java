package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoSMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RelatorioRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_FATURAMENTO_DETALHADO_DIARIO =
            "SELECT COD_PRODUTO, NME_PRODUTO, QTD_ITEM, " +
                    "VLR_PRECO_MEDIO, DSC_PERIODO FROM VW_FATURAMENTO_DETALHADO_DIARIO "
            ;

    private static final String SELECT_FATURAMENTO_DETALHADO_MENSAL =
            "SELECT COD_PRODUTO, NME_PRODUTO, QTD_ITEM, " +
                    "VLR_PRECO_MEDIO, DSC_PERIODO FROM VW_FATURAMENTO_DETALHADO_MENSAL "
            ;

    private static final String SELECT_FATURAMENTO_DETALHADO_ANUAL =
            "SELECT COD_PRODUTO, NME_PRODUTO, QTD_ITEM, " +
                    "VLR_PRECO_MEDIO, DSC_PERIODO FROM VW_FATURAMENTO_DETALHADO_ANUAL "
            ;

    private static final String SELECT_FATURAMENTO_SUMARIZADO_DIARIO =
            "SELECT COD_PRODUTO, VLR_CUSTO_VENDA, VLR_FATURADO, " +
                    "VLR_LIQUIDO, QTD_ITEM_ESTOQUE_ENTRADA, QTD_ITEM_ESTOQUE_SAIDA, DSC_PERIODO " +
                    "FROM VW_FATURAMENTO_SUMARIZADO_DIARIO "
            ;

    private static final String SELECT_FATURAMENTO_SUMARIZADO_MENSAL =
            "SELECT COD_PRODUTO, VLR_CUSTO_VENDA, VLR_FATURADO, " +
                    "VLR_LIQUIDO, QTD_ITEM_ESTOQUE_ENTRADA, QTD_ITEM_ESTOQUE_SAIDA, DSC_PERIODO " +
                    "FROM VW_FATURAMENTO_SUMARIZADO_MENSAL "
            ;

    private static final String SELECT_FATURAMENTO_SUMARIZADO_ANUAL =
            "SELECT COD_PRODUTO, VLR_CUSTO_VENDA, VLR_FATURADO, " +
                    "VLR_LIQUIDO, QTD_ITEM_ESTOQUE_ENTRADA, QTD_ITEM_ESTOQUE_SAIDA, DSC_PERIODO " +
                    "FROM VW_FATURAMENTO_SUMARIZADO_ANUAL "
            ;

    private static final String FILTRO_DSC_PERIODO =
            "WHERE DSC_PERIODO = ?"
            ;

    private static final String FILTRO_PERIODO_DSC_PERIODO =
            "WHERE DSC_PERIODO >= ? AND DSC_PERIODO <= ?"
            ;

    private static final String COLUNA_COD_PRODUTO = "COD_PRODUTO";
    private static final String COLUNA_NME_PRODUTO = "NME_PRODUTO";
    private static final String COLUNA_QTD_ITEM = "QTD_ITEM";
    private static final String COLUNA_VLR_PRECO_MEDIO = "VLR_PRECO_MEDIO";
    private static final String COLUNA_DSC_PERIODO = "DSC_PERIODO";
    private static final String COLUNA_VLR_CUSTO_VENDA = "VLR_CUSTO_VENDA";
    private static final String COLUNA_VLR_FATURADO = "VLR_FATURADO";
    private static final String COLUNA_VLR_LIQUIDO = "VLR_LIQUIDO";
    private static final String COLUNA_QTD_ITEM_ESTOQUE_ENTRADA = "QTD_ITEM_ESTOQUE_ENTRADA";
    private static final String COLUNA_QTD_ITEM_ESTOQUE_SAIDA = "QTD_ITEM_ESTOQUE_SAIDA";

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoDiario() {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_DETALHADO_DIARIO
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoDTO(
                                resultSet.getInt(COLUNA_COD_PRODUTO),
                                resultSet.getString(COLUNA_NME_PRODUTO),
                                resultSet.getInt(COLUNA_QTD_ITEM),
                                resultSet.getDouble(COLUNA_VLR_PRECO_MEDIO),
                                resultSet.getString(COLUNA_DSC_PERIODO)
                        )
        );
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoDiarioPeriodo
            (LocalDate dataInicio, LocalDate dataTermino) {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_DETALHADO_DIARIO + FILTRO_PERIODO_DSC_PERIODO
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoDTO(
                                resultSet.getInt(COLUNA_COD_PRODUTO),
                                resultSet.getString(COLUNA_NME_PRODUTO),
                                resultSet.getInt(COLUNA_QTD_ITEM),
                                resultSet.getDouble(COLUNA_VLR_PRECO_MEDIO),
                                resultSet.getString(COLUNA_DSC_PERIODO)
                        ),
                dataInicio, dataTermino
        );
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoMensalNull() {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_DETALHADO_MENSAL
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoDTO(
                                resultSet.getInt(COLUNA_COD_PRODUTO),
                                resultSet.getString(COLUNA_NME_PRODUTO),
                                resultSet.getInt(COLUNA_QTD_ITEM),
                                resultSet.getDouble(COLUNA_VLR_PRECO_MEDIO),
                                resultSet.getString(COLUNA_DSC_PERIODO)

                        )
        );
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoMensal(String anoMesFaturamento) {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_DETALHADO_MENSAL + FILTRO_DSC_PERIODO
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoDTO(
                                resultSet.getInt(COLUNA_COD_PRODUTO),
                                resultSet.getString(COLUNA_NME_PRODUTO),
                                resultSet.getInt(COLUNA_QTD_ITEM),
                                resultSet.getDouble(COLUNA_VLR_PRECO_MEDIO),
                                resultSet.getString(COLUNA_DSC_PERIODO)

                        ),
                anoMesFaturamento
        );
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoMensalPeriodo
            (String anoMesInicio, String anoMesTermino) {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_DETALHADO_MENSAL + FILTRO_PERIODO_DSC_PERIODO
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoDTO(
                                resultSet.getInt(COLUNA_COD_PRODUTO),
                                resultSet.getString(COLUNA_NME_PRODUTO),
                                resultSet.getInt(COLUNA_QTD_ITEM),
                                resultSet.getDouble(COLUNA_VLR_PRECO_MEDIO),
                                resultSet.getString(COLUNA_DSC_PERIODO)
                        ),
                anoMesInicio, anoMesTermino
        );
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoAnualNull() {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_DETALHADO_ANUAL
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoDTO(
                                resultSet.getInt(COLUNA_COD_PRODUTO),
                                resultSet.getString(COLUNA_NME_PRODUTO),
                                resultSet.getInt(COLUNA_QTD_ITEM),
                                resultSet.getDouble(COLUNA_VLR_PRECO_MEDIO),
                                resultSet.getString(COLUNA_DSC_PERIODO)
                        )
        );
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoAnual(String anoFaturamento) {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_DETALHADO_ANUAL + FILTRO_DSC_PERIODO
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoDTO(
                                resultSet.getInt(COLUNA_COD_PRODUTO),
                                resultSet.getString(COLUNA_NME_PRODUTO),
                                resultSet.getInt(COLUNA_QTD_ITEM),
                                resultSet.getDouble(COLUNA_VLR_PRECO_MEDIO),
                                resultSet.getString(COLUNA_DSC_PERIODO)
                        ),
                anoFaturamento
        );
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoAnualPeriodo
            (String anoInicial, String anoTermino) {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_DETALHADO_ANUAL + FILTRO_PERIODO_DSC_PERIODO
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoDTO(
                                resultSet.getInt(COLUNA_COD_PRODUTO),
                                resultSet.getString(COLUNA_NME_PRODUTO),
                                resultSet.getInt(COLUNA_QTD_ITEM),
                                resultSet.getDouble(COLUNA_VLR_PRECO_MEDIO),
                                resultSet.getString(COLUNA_DSC_PERIODO)
                        ),
                anoInicial, anoTermino
        );
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoDiario() {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_SUMARIZADO_DIARIO
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoSMO(
                                resultSet.getInt(COLUNA_COD_PRODUTO),
                                resultSet.getDouble(COLUNA_VLR_CUSTO_VENDA),
                                resultSet.getDouble(COLUNA_VLR_FATURADO),
                                resultSet.getDouble(COLUNA_VLR_LIQUIDO),
                                resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_ENTRADA),
                                resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_SAIDA),
                                resultSet.getString(COLUNA_DSC_PERIODO)
                        )
        );
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoDiarioPeriodo
            (LocalDate dataInicio, LocalDate dataTermino) {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_SUMARIZADO_DIARIO+ FILTRO_PERIODO_DSC_PERIODO
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoSMO(
                                resultSet.getInt(COLUNA_COD_PRODUTO),
                                resultSet.getDouble(COLUNA_VLR_CUSTO_VENDA),
                                resultSet.getDouble(COLUNA_VLR_FATURADO),
                                resultSet.getDouble(COLUNA_VLR_LIQUIDO),
                                resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_ENTRADA),
                                resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_SAIDA),
                                resultSet.getString(COLUNA_DSC_PERIODO)
                        ),
                dataInicio, dataTermino
        );
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoMensalNull() {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_SUMARIZADO_MENSAL
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoSMO(
                                resultSet.getInt(COLUNA_COD_PRODUTO),
                                resultSet.getDouble(COLUNA_VLR_CUSTO_VENDA),
                                resultSet.getDouble(COLUNA_VLR_FATURADO),
                                resultSet.getDouble(COLUNA_VLR_LIQUIDO),
                                resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_ENTRADA),
                                resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_SAIDA),
                                resultSet.getString(COLUNA_DSC_PERIODO)
                        )
        );
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoMensal(String anoMesFaturamento) {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_SUMARIZADO_MENSAL + FILTRO_DSC_PERIODO
                    , (resultSet, rowNum) ->
                    new ProdutoRelatorioFaturamentoSMO(
                            resultSet.getInt(COLUNA_COD_PRODUTO),
                            resultSet.getDouble(COLUNA_VLR_CUSTO_VENDA),
                            resultSet.getDouble(COLUNA_VLR_FATURADO),
                            resultSet.getDouble(COLUNA_VLR_LIQUIDO),
                            resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_ENTRADA),
                            resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_SAIDA),
                            resultSet.getString(COLUNA_DSC_PERIODO)
                    ),
                anoMesFaturamento
        );
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoMensalPeriodo
            (String anoMesInicial, String anoMesTermino) {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_SUMARIZADO_MENSAL + FILTRO_PERIODO_DSC_PERIODO
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoSMO(
                                resultSet.getInt(COLUNA_COD_PRODUTO),
                                resultSet.getDouble(COLUNA_VLR_CUSTO_VENDA),
                                resultSet.getDouble(COLUNA_VLR_FATURADO),
                                resultSet.getDouble(COLUNA_VLR_LIQUIDO),
                                resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_ENTRADA),
                                resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_SAIDA),
                                resultSet.getString(COLUNA_DSC_PERIODO)
                        ),
                anoMesInicial, anoMesTermino
        );
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoAnualNull() {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_SUMARIZADO_ANUAL
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoSMO(
                                resultSet.getInt(COLUNA_COD_PRODUTO),
                                resultSet.getDouble(COLUNA_VLR_CUSTO_VENDA),
                                resultSet.getDouble(COLUNA_VLR_FATURADO),
                                resultSet.getDouble(COLUNA_VLR_LIQUIDO),
                                resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_ENTRADA),
                                resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_SAIDA),
                                resultSet.getString(COLUNA_DSC_PERIODO)
                        )
        );
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoAnual(String anoFaturamento) {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_SUMARIZADO_ANUAL + FILTRO_DSC_PERIODO
                    , (resultSet, rowNum) ->
                    new ProdutoRelatorioFaturamentoSMO(
                            resultSet.getInt(COLUNA_COD_PRODUTO),
                            resultSet.getDouble(COLUNA_VLR_CUSTO_VENDA),
                            resultSet.getDouble(COLUNA_VLR_FATURADO),
                            resultSet.getDouble(COLUNA_VLR_LIQUIDO),
                            resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_ENTRADA),
                            resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_SAIDA),
                            resultSet.getString(COLUNA_DSC_PERIODO)
                    ),
                anoFaturamento
        );
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoAnualPeriodo
            (String anoInicial, String anoTermino) {
        return jdbcTemplate.query(
                SELECT_FATURAMENTO_SUMARIZADO_ANUAL + FILTRO_PERIODO_DSC_PERIODO
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoSMO(
                                resultSet.getInt(COLUNA_COD_PRODUTO),
                                resultSet.getDouble(COLUNA_VLR_CUSTO_VENDA),
                                resultSet.getDouble(COLUNA_VLR_FATURADO),
                                resultSet.getDouble(COLUNA_VLR_LIQUIDO),
                                resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_ENTRADA),
                                resultSet.getDouble(COLUNA_QTD_ITEM_ESTOQUE_SAIDA),
                                resultSet.getString(COLUNA_DSC_PERIODO)
                        ),
                anoInicial, anoTermino
        );
    }
}

package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RelatorioRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamento() {
        return jdbcTemplate.query("SELECT COD_PRODUTO, NME_PRODUTO, QTD_ITEM, " +
                        "VLR_PRECO_MEDIO, DSC_PERIODO FROM VW_FATURAMENTO_DETALHADO_DIARIO"
                , (resultSet, rowNum) ->
                        new ProdutoRelatorioFaturamentoDTO(
                                resultSet.getInt("COD_PRODUTO"),
                                resultSet.getString("NME_PRODUTO"),
                                resultSet.getInt("QTD_ITEM"),
                                resultSet.getDouble("VLR_PRECO_MEDIO"),
                                resultSet.getString("DSC_PERIODO")
                        )
        );
    }

}

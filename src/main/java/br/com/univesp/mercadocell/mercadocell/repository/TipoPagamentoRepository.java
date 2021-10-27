package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.TipoPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoPagamentoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void cadastrarTipoPagamento(TipoPagamento tipoPagamento){
        jdbcTemplate.update("INSERT INTO TIPO_PAGAMENTO (NME_TIPO_PAGAMENTO ) VALUES (?)",
                tipoPagamento.getNomeTipoPagamento());
    }

    public TipoPagamento buscarTipoPagamentoPorId(int idTipoPagamento){
        try {
            return jdbcTemplate.queryForObject("SELECT COD_TIPO_PAGAMENTO, NME_TIPO_PAGAMENTO FROM `TIPO_PAGAMENTO` WHERE `COD_TIPO_PAGAMENTO` = ?"
                    , (rs, rowNum) ->
                            new TipoPagamento(
                                    rs.getInt("COD_TIPO_PAGAMENTO"),
                                    rs.getString("NME_TIPO_PAGAMENTO")
                            ),
                    new Object[]{idTipoPagamento}
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<TipoPagamento> listarTiposPagamento(){
        return jdbcTemplate.query("SELECT COD_TIPO_PAGAMENTO, NME_TIPO_PAGAMENTO FROM `TIPO_PAGAMENTO`"
                , (rs, rowNum) ->
                        new TipoPagamento(
                                rs.getInt("COD_TIPO_PAGAMENTO"),
                                rs.getString("NME_TIPO_PAGAMENTO")
                        )
        );
    }

    public void atualizarTipoPagamento(TipoPagamento tipoPagamento){
        jdbcTemplate.update(
                "UPDATE `TIPO_PAGAMENTO` SET `NME_TIPO_PAGAMENTO` = ? WHERE `COD_TIPO_PAGAMENTO` = ?",
                tipoPagamento.getNomeTipoPagamento(),
                tipoPagamento.getCodTipoPagamento()
        );
    }

    public void deletarTipoPagamento(int idTipoPagamento){
        jdbcTemplate.update(
                "DELETE FROM `TIPO_PAGAMENTO` TP WHERE `TP`.`COD_TIPO_PAGAMENTO` = ?",
                idTipoPagamento
        );
    }
}

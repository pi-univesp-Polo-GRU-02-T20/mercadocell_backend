package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.TipoPagamento;
import br.com.univesp.mercadocell.mercadocell.repository.TipoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPagamentoService {

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    //TODO ADD VERIFICAÇÃO DE TIPO PGTO EXISTENTE NA BASE ANTES DE CADASTRAR
    public void cadastrarTipoPagamento(TipoPagamento tipoPagamento) {
        tipoPagamentoRepository.cadastrarTipoPagamento(tipoPagamento);
    }

    public TipoPagamento buscarTipoPagamentoPorId(int idTipoPagamento) {
        return tipoPagamentoRepository.buscarTipoPagamentoPorId(idTipoPagamento);
    }

    public List<TipoPagamento> listarTiposPagamento() {
        return tipoPagamentoRepository.listarTiposPagamento();
    }

    public void atualizarTipoPagamento(TipoPagamento tipoPagamento) {
        tipoPagamentoRepository.atualizarTipoPagamento(tipoPagamento);
    }
    // TODO ADD VERIFICAÇÃO DE ERRO DE FK ( COMPRA/VENDA VINCULADA AO TIPO DE PGTO A SER APAGADO)
    public void deletarTipoPagamento(int idTipoPagamento) {
        tipoPagamentoRepository.deletarTipoPagamento(idTipoPagamento);
    }
}

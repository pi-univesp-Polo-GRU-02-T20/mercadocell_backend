package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.TipoPagamento;
import br.com.univesp.mercadocell.mercadocell.repository.TipoPagamentoRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPagamentoService {

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    public void cadastrarTipoPagamento(TipoPagamento tipoPagamento) {
        try {
            tipoPagamentoRepository.buscarTipoPagamentoPorId(tipoPagamento.getCodTipoPagamento());
            throw new EntityIntegrityViolationException("Tipo de pagamento já cadastrado: " +
                    tipoPagamento.getCodTipoPagamento());
        }catch (EmptyResultDataAccessException e ){
            try {
                tipoPagamentoRepository.cadastrarTipoPagamento(tipoPagamento);
            } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new EntityIntegrityViolationException(
                    "Dados de Tipo de Pagamento inconsistentes: " + tipoPagamento.toString());
            }
        }
    }

    public TipoPagamento buscarTipoPagamentoPorId(int idTipoPagamento) {
        try{
            return tipoPagamentoRepository.buscarTipoPagamentoPorId(idTipoPagamento);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Tipo de pagamento já cadastrado: " + idTipoPagamento);
        }
    }

    public List<TipoPagamento> listarTiposPagamento() {
        try{
            return tipoPagamentoRepository.listarTiposPagamento();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException("Nenhum registro encontrado");
        }
    }

    public void atualizarTipoPagamento(TipoPagamento tipoPagamento) {
        tipoPagamentoRepository.atualizarTipoPagamento(tipoPagamento);
    }

    public void deletarTipoPagamento(int idTipoPagamento) {
        try {
            tipoPagamentoRepository.deletarTipoPagamento(idTipoPagamento);
        } catch ( DataIntegrityViolationException dataException) {
            throw new EntityIntegrityViolationException(
                    "Pagamento utilizada no cadastro de compras ou vendas: "+ idTipoPagamento);
        }
    }
}

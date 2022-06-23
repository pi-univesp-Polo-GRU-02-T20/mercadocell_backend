package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.PagamentoOperacao;
import br.com.univesp.mercadocell.mercadocell.repository.PagamentoOperacaoRespository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoOperacaoService {

    @Autowired
    PagamentoOperacaoRespository pagamentoOperacaoRespository;

    public void incluirPagamentoOperacao(PagamentoOperacao pagamentoOperacao) {
        try {
            pagamentoOperacaoRespository.incluirPagamentoOperacao(pagamentoOperacao);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new EntityIntegrityViolationException(
                    "Dados de Pagamento inconsistentes:" + pagamentoOperacao.toString());
        }
    }

    public void removerPagamentoOperacao(Integer idPagamento) {
        pagamentoOperacaoRespository.removerPagamentoOperacao(idPagamento);
    }

    public List<PagamentoOperacao> buscarPagamentoPorOperacaoId(Integer idOperacao) {
       return pagamentoOperacaoRespository.buscarPagamentoPorOperacaoId(idOperacao);
    }


    public List<PagamentoOperacao> listarPagamentoPorTipoOperacao(String tipoOperacao) {
        return pagamentoOperacaoRespository.listarPagamentoPorTipoOperacao(tipoOperacao);
    }
}


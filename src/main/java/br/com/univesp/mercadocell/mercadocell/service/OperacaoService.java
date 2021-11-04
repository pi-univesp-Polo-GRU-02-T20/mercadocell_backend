package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Operacao;
import br.com.univesp.mercadocell.mercadocell.repository.OperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OperacaoService {

    @Autowired
    private OperacaoRepository operacaoRepository;
    private OperacaoItemProdutoRepository operacaoItemProdutoRepository;

    public void cadastrarOperacao(Operacao operacao){
        operacaoRepository.cadastrarOperacao(operacao);
    }

    public Operacao buscarOperacaoPorId(int idOperacao) {
        return operacaoRepository.buscarOperacaoPorId(idOperacao);
    }

    public List<Operacao> listarOperacoes() {
        return operacaoRepository.listarOperacoes();
    }

    public void atualizarOperacao(Operacao operacao) {
        operacaoRepository.atualizarOperacao(operacao);
    }

    public void deletarOperacao(int idOperacao) {
        operacaoRepository.deletarOperacao(idOperacao);
    }

    public List<Operacao> listarOperacoesPorPessoa(int idPessoa, String tipoOperacao) {
        return operacaoRepository.listarOperacoesPorPessoa(idPessoa, tipoOperacao);
    }

    public List<Operacao> listarOperacoesPagas(boolean pago) {
        return operacaoRepository.listarOperacoesPagas(pago);
    }
    public List<Operacao> listarOperacoesPorPeriodo(LocalDate dataInicio, LocalDate dataTermino) {
        return operacaoRepository.listarOperacoesPorPeriodo(dataInicio, dataTermino);
    }

}

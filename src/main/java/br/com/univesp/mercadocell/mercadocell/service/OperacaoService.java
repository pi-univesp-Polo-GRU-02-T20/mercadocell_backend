package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Operacao;
import br.com.univesp.mercadocell.mercadocell.repository.OperacaoRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import br.com.univesp.mercadocell.mercadocell.service.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OperacaoService {

    @Autowired
    private OperacaoRepository operacaoRepository;

    public void cadastrarOperacao(Operacao operacao){
        operacaoRepository.cadastrarOperacao(operacao);
    }

    public Operacao buscarOperacaoPorId(int idOperacao) {
        try{
            return operacaoRepository.buscarOperacaoPorId(idOperacao);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Código de operação não encontrado: =" + idOperacao
            );
        }
    }

    public List<Operacao> listarOperacoes() {
        try{
            return operacaoRepository.listarOperacoes();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(Mensagens.NO_REG_FOUND);
        }
    }


    public void atualizarOperacao(Operacao operacao) {
        try{
            operacaoRepository.atualizarOperacao(operacao);
        }catch(DataIntegrityViolationException e ){
            throw new EntityIntegrityViolationException(
                    "A pessoa informada não foi cadastrada na base: " + operacao.toString());
        }
    }

    public void deletarOperacao(int idOperacao) {
        try {
            operacaoRepository.deletarOperacao(idOperacao);
        } catch (DataIntegrityViolationException e) {
            throw new EntityIntegrityViolationException(
                    "Operação vinculada a itens e pagamentos: " + idOperacao
            );
        }
    }

    public List<Operacao> listarOperacoesPorPessoa(int idPessoa, String tipoOperacao) {
        try{
            return operacaoRepository.listarOperacoesPorPessoa(idPessoa, tipoOperacao);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(Mensagens.NO_REG_FOUND);
        }
    }

    public List<Operacao> listarOperacoesPagas(boolean pago, String tipoIOperacao) {
        try{
            return operacaoRepository.listarOperacoesPagas(pago, tipoIOperacao);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(Mensagens.NO_REG_FOUND);
        }
    }
    public List<Operacao> listarOperacoesPorPeriodo(LocalDate dataInicio, LocalDate dataTermino, String tipoOperacao) {
        try{
            return operacaoRepository.listarOperacoesPorPeriodo(dataInicio, dataTermino, tipoOperacao);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(Mensagens.NO_REG_FOUND);
        }
    }

}

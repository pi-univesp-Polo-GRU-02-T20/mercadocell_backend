package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.ItemOperacao;
import br.com.univesp.mercadocell.mercadocell.model.dto.ItemOperacaoDTO;
import br.com.univesp.mercadocell.mercadocell.repository.ItemOperacaoRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemOperacaoService {

    @Autowired
    ItemOperacaoRepository itemOperacaoRepository;

    public List<ItemOperacaoDTO> listarItensOperacaoPorCategoria(String nomeCategoria, String tipoOperacao) {
        try{
            return itemOperacaoRepository.listarOperacoesPorCategoria(nomeCategoria, tipoOperacao);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Itens não encontrados com os críterios informados :" +
                            " nomeCategoria = " + nomeCategoria  + " "+
                            " tipoOperacao = " + tipoOperacao  + " "
            );
        }
    }

    public List<ItemOperacaoDTO> listarItensOperacaoPorProduto(String nomeProduto, String tipoOperacao) {
        try{
            return itemOperacaoRepository.listarItensOperacaoPorProduto(nomeProduto, tipoOperacao);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Itens não encontrados com os críterios informados :" +
                            " nomeProduto = " + nomeProduto  + " "+
                            " tipoOperacao = " + tipoOperacao  + " "
            );
        }
    }

    public List<ItemOperacaoDTO> listarItensOperacaoPorTipoOperacao(String tipoOperacao) {
        try{
            return itemOperacaoRepository.listarItensOperacaoPorTipoOperacao(tipoOperacao);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Itens não encontrados com os críterios informados :" +
                            " tipoOperacao = " + tipoOperacao
            );
        }
    }

    @Transactional
    public void incluirItensOperacao(List<ItemOperacao> listaItensOperacao) {
        for (ItemOperacao item : listaItensOperacao) {
            try {
                itemOperacaoRepository.incluirItemOperacao(item);
            } catch (DataIntegrityViolationException e) {
                throw new EntityIntegrityViolationException(
                        "Produto, Compra ou venda não cadastrado na base: " + item.toString());
            }
        }
    }

    @Transactional
    public void removerItensOperacao(List<ItemOperacao> listaItensOperacao) {
        for (ItemOperacao item : listaItensOperacao) {
            try {
                itemOperacaoRepository.incluirItemOperacao(item);
            } catch (DataIntegrityViolationException e) {
                throw new EntityIntegrityViolationException(
                        "Erro ao remover items de compra ou venda: " +
                                itemOperacaoRepository.toString()+ " "+
                                e.getMessage()
                );
            }
        }
    }
}

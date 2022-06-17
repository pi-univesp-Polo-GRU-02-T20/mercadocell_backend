package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.dto.ItemOperacaoDTO;
import br.com.univesp.mercadocell.mercadocell.model.ItemOperacao;
import br.com.univesp.mercadocell.mercadocell.repository.ItemOperacaoRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import br.com.univesp.mercadocell.mercadocell.service.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
public class ItemOperacaoService {

    @Autowired
    ItemOperacaoRepository itemOperacaoRepository;

    public List<ItemOperacao> listarItensOperacaoPorCategoria(String nomeCategoria, String tipoOperacao) {
        try{

            return itemOperacaoRepository.listarOperacoesPorCategoria(nomeCategoria, tipoOperacao);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    Mensagens.NO_ITEM_FOUND_FILTER +
                            " nomeCategoria = " + nomeCategoria  + " "+
                            "tipoOperacao = " + tipoOperacao
            );
        }
    }

    private ItemOperacaoDTO converteItemOperacaoParaItemOperacaoDTO(ItemOperacao itemOperacao){
        return new ItemOperacaoDTO(
                itemOperacao.getCodItemOperacao(),
                itemOperacao.getQuantidadeItem(),
                itemOperacao.getValorItem(),
                OperacaoService.converteOperacaoParaOpereacaoDTO(itemOperacao.getOperacao()),
                ProdutoService.converteProdutoParaProdutoDTO(itemOperacao.getProduto())
        );
    }

    public List<ItemOperacao> listarItensOperacaoPorProduto(String nomeProduto, String tipoOperacao) {
        try{
            return itemOperacaoRepository.listarItensOperacaoPorProduto(nomeProduto, tipoOperacao);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    Mensagens.NO_ITEM_FOUND_FILTER +
                            " nomeProduto = " + nomeProduto  + " "+
                            "tipoOperacao = " + tipoOperacao
            );
        }
    }

    public List<ItemOperacao> listarItensOperacaoPorTipoOperacao(String tipoOperacao) {
        try{
            return itemOperacaoRepository.listarItensOperacaoPorTipoOperacao(tipoOperacao);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    Mensagens.NO_ITEM_FOUND_FILTER +
                            " tipoOperacao = " + tipoOperacao
            );
        }
    }

    @Transactional
    public void incluirItensOperacao(@Valid List<ItemOperacao> listaItensOperacao) {
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

package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.ItemOperacao;
import br.com.univesp.mercadocell.mercadocell.model.Operacao;
import br.com.univesp.mercadocell.mercadocell.repository.ItemOperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemOperacaoService {

    @Autowired
    ItemOperacaoRepository itemOperacaoRepository;
    public List<ItemOperacao> listarItensOperacaoPorCategoria(String nomeCategoria, String tipoOperacao) {
        return itemOperacaoRepository.listarOperacoesPorCategoria(nomeCategoria, tipoOperacao);
    }
    public List<ItemOperacao> listarItensOperacaoPorProduto(String nomeProduto, String tipoOperacao) {
        return itemOperacaoRepository.listarItensOperacaoPorProduto(nomeProduto, tipoOperacao);
    }

    public void incluirItensOperacao(List<ItemOperacao> listaItensOperacao) {
        itemOperacaoRepository.incluirItensOperacao(listaItensOperacao);
    }
}

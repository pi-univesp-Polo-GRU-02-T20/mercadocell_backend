package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Produto;
import br.com.univesp.mercadocell.mercadocell.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void cadastrarProduto(Produto produto) {
        produtoRepository.cadastrarProduto(produto);
    }

    public Produto buscarProdutoPorId(int  idProduto) {
        return produtoRepository.buscarProdutoPorId(idProduto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.listarProdutos();
    }

    public void atualizarProduto(Produto produto) {
        produtoRepository.atualizarProduto(produto);
    }

    public void deletarProduto(int idProduto) {
        produtoRepository.deletarProduto(idProduto);
    }
}

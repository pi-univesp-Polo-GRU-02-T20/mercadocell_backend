package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Produto;
import br.com.univesp.mercadocell.mercadocell.repository.ProdutoRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void cadastrarProduto(Produto produto) {
       try {
            produtoRepository.buscarProdutoPorNome(produto.getNomeProduto());
            throw new EntityIntegrityViolationException("Produto já cadastrado: " + produto.toString());
        }catch (EmptyResultDataAccessException e){
           produtoRepository.cadastrarProduto(produto);
        }
    }

    public Produto buscarProdutoPorId(int  idProduto) {
        try{
            return produtoRepository.buscarProdutoPorId(idProduto);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Código de produto não encontrado: " + idProduto);
        }
    }

    public List<Produto> listarProdutos() {
        try{
            return produtoRepository.listarProdutos();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException("Nenhum registro encontrado");
        }
    }

    public void atualizarProduto(Produto produto) {
        try{
            produtoRepository.atualizarProduto(produto);
        }catch(DataIntegrityViolationException e ){
            throw new EntityIntegrityViolationException(
                    "Subcategoria ou Unidade de medida não cadastrada na base: " + produto.toString());
        }
    }

    public void deletarProduto(int idProduto) {
        try {
            produtoRepository.deletarProduto(idProduto);
        } catch (DataIntegrityViolationException e) {
            throw new EntityIntegrityViolationException(
                    "Produto utilizado no registro de compra ou venda: " + idProduto
            );
        }
    }
}

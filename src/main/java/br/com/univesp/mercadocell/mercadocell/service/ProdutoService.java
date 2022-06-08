package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Imagem;
import br.com.univesp.mercadocell.mercadocell.model.Produto;
import br.com.univesp.mercadocell.mercadocell.repository.ProdutoRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void cadastrarProduto(Produto produto, MultipartFile imagem) {
       try {
            produtoRepository.buscarProdutoPorNome(produto.getNomeProduto());
            throw new EntityIntegrityViolationException("Produto já cadastrado: " + produto.toString());
        }catch (EmptyResultDataAccessException e){
           try {
               produto.setImagem(new Imagem(
                                        StringUtils.cleanPath(imagem.getOriginalFilename()),
                                        imagem.getContentType(),
                                        imagem.getBytes())
               );
               produtoRepository.cadastrarProduto(produto);

           } catch (DataIntegrityViolationException dataIntegrityViolationException) {
               throw new EntityIntegrityViolationException(
               "Subcategoria ou Unidade de Medida informada não foi cadastrada na base:" + produto.toString());
           }catch( IOException ioException){
               throw new EntityIntegrityViolationException(
                       "Subcategoria ou Unidade de Medida informada não foi cadastrada na base:" + produto.toString());
           }

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

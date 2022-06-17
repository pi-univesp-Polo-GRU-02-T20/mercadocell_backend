package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.dto.ProdutoDTO;
import br.com.univesp.mercadocell.mercadocell.model.Imagem;
import br.com.univesp.mercadocell.mercadocell.model.ItemOperacao;
import br.com.univesp.mercadocell.mercadocell.model.Produto;
import br.com.univesp.mercadocell.mercadocell.repository.ProdutoRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ImagemService imagemService;

    @Transactional
    public void cadastrarProduto(Produto produto) {
       try {
            produtoRepository.buscarProdutoPorNome(produto.getNomeProduto());
            throw new EntityIntegrityViolationException("Produto já cadastrado: " + produto.toString());
        }catch (EmptyResultDataAccessException e){
           produtoRepository.cadastrarProduto(produto);
       } catch (DataIntegrityViolationException dataIntegrityViolationException) {
               throw new EntityIntegrityViolationException(
               "Subcategoria ou Unidade de Medida informada não foi cadastrada na base:" + produto.toString());
        }
    }

    public ProdutoDTO buscarProdutoPorId(int  idProduto) {
       try{
            Produto produto =  produtoRepository.buscarProdutoPorId(idProduto);
            return new ProdutoDTO(
                    produto.getCodProduto(),
                    produto.getNomeProduto(),
                    produto.getDescricaoProduto(),
                    produto.getSubCategoria().getCodSubCategoria(),
                    produto.getUnidadeMedida().getCodUnidadeMedida(),
                    produto.getQuantidadeEstoqueMinima(),
                    produto.getQuantidadeEstoqueMaxima(),
                    produto.getQuantidadeEstoqueAtual()
            );
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

    @Transactional
    public void deletarProduto(int idProduto) {
        List<Imagem> listaImg = imagemService.buscarImagemProdutoPorId(idProduto);
        for (Imagem  img : listaImg) {
            try {
                imagemService.removerImagemProduto(idProduto, img);
            } catch (DataIntegrityViolationException e) {
                throw new EntityIntegrityViolationException(
                        "Erro ao desvincular imagens do produto: " +
                                img.toString()+ " "+
                                e.getMessage()
                );
            }
        }
        try {
            produtoRepository.deletarProduto(idProduto);
        } catch (DataIntegrityViolationException e) {
            throw new EntityIntegrityViolationException(
                    "Produto utilizado no registro de compra ou venda: " + idProduto
            );
        }
    }
}


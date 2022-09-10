package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.dto.ImagemProdutoDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ProdutoDTO;
import br.com.univesp.mercadocell.mercadocell.model.Imagem;
import br.com.univesp.mercadocell.mercadocell.model.Produto;
import br.com.univesp.mercadocell.mercadocell.model.SubCategoria;
import br.com.univesp.mercadocell.mercadocell.model.UnidadeMedida;
import br.com.univesp.mercadocell.mercadocell.repository.ProdutoRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ImagemService imagemService;

    public void cadastrarProduto(ProdutoDTO produtoDTO) {
        try {
            produtoRepository.buscarProdutoPorNome(produtoDTO.getNomeProduto());
            throw new EntityIntegrityViolationException("Produto já cadastrado: " + produtoDTO.toString());
        }catch (EmptyResultDataAccessException e){
            try {
                produtoRepository.cadastrarProduto(converteProdutoDTOParaProduto(produtoDTO));
            } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                throw new EntityIntegrityViolationException(
                        "Dados de Produto Inconsistentes:" + produtoDTO.toString());
            }
        }
    }

    @Transactional
    public void cadastrarProdutoComImagem(ProdutoDTO produtoDTO, MultipartFile arqImagem) {
       try {
            produtoRepository.buscarProdutoPorNome(produtoDTO.getNomeProduto());
            throw new EntityIntegrityViolationException("Produto já cadastrado: " + produtoDTO.toString());
        }catch (EmptyResultDataAccessException e){
            try {
                int codProduto = produtoRepository.cadastrarProduto(converteProdutoDTOParaProduto(produtoDTO));
                produtoDTO.setCodProduto(codProduto);
                imagemService.cadastrarImagemProduto(codProduto, imagemService.converteMultipartFileParaImagem(arqImagem));
            } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                    throw new EntityIntegrityViolationException(
                   "Dados de Produto ou Imagem Inconsistentes:" + produtoDTO.toString());
            }
       }

    }

    public ProdutoDTO buscarProdutoPorId(int  idProduto) {
       try{
           Produto produto = produtoRepository.buscarProdutoPorId(idProduto);
           return converteProdutoParaProdutoDTO(produto);
       } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Código de produto não encontrado: " + idProduto);
       }
    }

    public List<ProdutoDTO> listarProdutos() {
        List<ProdutoDTO> listaProdutosDTO = new ArrayList<ProdutoDTO>();
        for (Produto produto :  produtoRepository.listarProdutos() ){
            ProdutoDTO produtoDTO = converteProdutoParaProdutoDTO(produto);
            listaProdutosDTO.add(produtoDTO);
        }
        /*
        try{

            System.out.println(listaProdutos.toString());
            for (Produto produto : listaProdutos ){
                ProdutoDTO produtoDTO = converteProdutoParaProdutoDTO(produto);
                produtoDTO.setListaImagem(imagemService.buscarImagemProdutoPorId(produto.getCodProduto()));
                listaProdutosDTO.add(produtoDTO);
            }
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException("Nenhum registro encontrado");
        }
         */
        return listaProdutosDTO ;
    }

    /*
    *   @Transactional
    public void incluirItensOperacao(@Valid List<ItemOperacao> listaItensOperacao) {
        for (ItemOperacao item : listaItensOperacao) {
            try {
                itemOperacaoRepository.incluirItemOperacao(item);
            } catch (DataIntegrityViolationException e) {
                throw new EntityIntegrityViolationException(
                        "Dados de Item de Operação de compra ou venda inconsistentes: " + item.toString());
            }
        }
    }
    * */


    @Transactional
    public void atualizarProduto(ProdutoDTO produtoDTO) {
        try{
            produtoRepository.atualizarProduto(converteProdutoDTOParaProduto(produtoDTO));

        }catch(DataIntegrityViolationException e ){
            throw new EntityIntegrityViolationException(
                    "Subcategoria ou Unidade de medida não cadastrada na base: " + produtoDTO.toString());
        }
        // produto  1:1 imagem
        //TODO: add imagem e mudar cardinalidade produto 1:N imagem;
      //  imagemService.atualizarImagem(imagemService.converteMultipartFileParaImagem(produtoDTO.getArqImagem()));
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

    // métodos de apoio
    public static ProdutoDTO converteProdutoParaProdutoDTO(Produto produto) {
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
    }
    public static Produto converteProdutoDTOParaProduto(ProdutoDTO produtoDTO) {
        return new Produto(
                produtoDTO.getCodProduto(),
                produtoDTO.getNomeProduto(),
                produtoDTO.getDescricaoProduto(),
                new SubCategoria(
                        produtoDTO.getCodigoSubcategoria()
                ),
                new UnidadeMedida(
                        produtoDTO.getCodigoUnidadeMedida()
                ),
                produtoDTO.getCodigoUnidadeMedida(),
                produtoDTO.getQuantidadeEstoqueMinimo(),
                produtoDTO.getQuantidadeEstoqueAtual()
        );
    }

}


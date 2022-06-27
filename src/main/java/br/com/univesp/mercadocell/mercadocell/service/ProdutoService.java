package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.dto.ImagemProdutoDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ProdutoConsultaDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ProdutoInputDTO;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ImagemService imagemService;

    @Transactional
    public void cadastrarProduto(ProdutoInputDTO produtoInputDTO) {
       try {
            produtoRepository.buscarProdutoPorNome(produtoInputDTO.getNomeProduto());
            throw new EntityIntegrityViolationException("Produto já cadastrado: " + produtoInputDTO.toString());
        }catch (EmptyResultDataAccessException e){
            try {
                produtoRepository.cadastrarProduto(converteProdutoDTOParaProduto(produtoInputDTO));
            } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                    throw new EntityIntegrityViolationException(
                   "Dados de Produto Inconsistentes:" + produtoInputDTO.toString());
            }
       }
       imagemService.cadastrarImagem(imagemService.converteMultipartFileParaImagem(produtoInputDTO.getArqImagem()));
       imagemService.vincularImagemProduto(
                    new ImagemProdutoDTO(
                                            imagemService.getCodImagemProdutoCadastrada(),
                                            produtoRepository.getCodProdutoCadastrado()
                                        )
       );
    }

    @Transactional
    public ProdutoConsultaDTO buscarProdutoPorId(int  idProduto) {
       ProdutoConsultaDTO produtoConsultaDTO = null;
       Produto produto = null;
       try{
           produto = produtoRepository.buscarProdutoPorId(idProduto);
           //return converteProdutoParaProdutoDTO(produto);
       } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Código de produto não encontrado: " + idProduto);
       }
        produtoConsultaDTO.setListaImagem(imagemService.buscarImagemProdutoPorId(idProduto));
       return produtoConsultaDTO;
    }

    public List<ProdutoConsultaDTO> listarProdutos() {
        List<ProdutoConsultaDTO> listaProdutosDTO = new ArrayList<ProdutoConsultaDTO>();
        try{
            List<Produto> listaProdutos = produtoRepository.listarProdutos();
            System.out.println(listaProdutos.toString());
            for (Produto produto : listaProdutos ){
                ProdutoConsultaDTO produtoConsultaDTO = converteProdutoParaProdutoConsultaDTO(produto);
                produtoConsultaDTO.setListaImagem(imagemService.buscarImagemProdutoPorId(produto.getCodProduto()));
                listaProdutosDTO.add(produtoConsultaDTO);
            }
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException("Nenhum registro encontrado");
        }
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
    public void atualizarProduto(ProdutoInputDTO produtoInputDTO) {
        try{
            produtoRepository.atualizarProduto(converteProdutoDTOParaProduto(produtoInputDTO));

        }catch(DataIntegrityViolationException e ){
            throw new EntityIntegrityViolationException(
                    "Subcategoria ou Unidade de medida não cadastrada na base: " + produtoInputDTO.toString());
        }
        // produto  1:1 imagem
        //TODO mudar cardinalidade produto 1:N imagem
        imagemService.atualizarImagem(imagemService.converteMultipartFileParaImagem(produtoInputDTO.getArqImagem()));
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
    public static ProdutoInputDTO converteProdutoParaProdutoDTO(Produto produto) {
        return new ProdutoInputDTO(
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


    // métodos de apoio
    public static ProdutoConsultaDTO converteProdutoParaProdutoConsultaDTO(Produto produto) {
        return new ProdutoConsultaDTO(
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

    public static Produto converteProdutoDTOParaProduto(ProdutoInputDTO produtoInputDTO) {
        return new Produto(
                produtoInputDTO.getCodProduto(),
                produtoInputDTO.getNomeProduto(),
                produtoInputDTO.getDescricaoProduto(),
                new SubCategoria(
                            produtoInputDTO.getCodigoSubCategoria(),
                        null
                )
                ,new UnidadeMedida(
                        produtoInputDTO.getCodigoUnidadeMedida(),
                        null
                ),
                produtoInputDTO.getQuantidadeEstoqueMinima(),
                produtoInputDTO.getQuantidadeEstoqueMaxima(),
                produtoInputDTO.getQuantidadeEstoqueAtual()
        );
    }

}


package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.dto.ImagemProdutoDTO;
import br.com.univesp.mercadocell.mercadocell.model.Imagem;
import br.com.univesp.mercadocell.mercadocell.repository.ImagemRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import br.com.univesp.mercadocell.mercadocell.service.exception.FileHandleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImagemService {
    @Autowired
    private ImagemRepository imagemRepository;

    public void cadastrarImagem(Imagem imagem) {
        //Imagem img =  converteMultipartFileParaImagem(arqImagem);
        try{
            imagemRepository.buscarImagemPorNome(imagem.getNomeImagem());
        }catch(EmptyResultDataAccessException emptyResultDataAccessException){
            imagemRepository.cadastrarImagem(imagem);
        }
    }

    public int cadastrarImagem(Integer produtoId,  Imagem imagem) {
       // Imagem img =  converteMultipartFileParaImagem(arqImagem);
        int imagemId = 0;
        try{
            imagemRepository.buscarImagemPorNome(imagem.getNomeImagem());
        }catch(EmptyResultDataAccessException emptyResultDataAccessException){
            imagemId = imagemRepository.cadastrarImagem(produtoId, imagem);
            ImagemProdutoDTO imagemProdutoDTO = new ImagemProdutoDTO(
                    imagemId, produtoId
            );
            vincularImagemProduto(imagemProdutoDTO   );
        }
        return imagemId;
    }


    public void atualizarImagem(Imagem imagem) {
            imagemRepository.atualizarImagem(imagem);
    }

    public Imagem buscarImagemPorId(int  idImagem) {
        try {
            return imagemRepository.buscarImagemPorId(idImagem);
        }catch(EmptyResultDataAccessException e) {
            throw  new EntityNotFoundException("Nenhuma imagem foi encontrada com o id: " + idImagem);
        }
    }

    public List<Imagem> buscarImagemProdutoPorId(int  idProduto) {
        try {
            return imagemRepository.buscarImagemProdutoPorId(idProduto);
        }catch(EmptyResultDataAccessException e) {
            throw  new EntityNotFoundException("Nenhuma imagem foi encontrada para o produto: " + idProduto);
        }
    }



    public void removerImagemProduto(int  idProduto, Imagem imagem){
        try {
            imagemRepository.removerVinculoImagemProduto(idProduto, imagem.getCodigoImagem());
        } catch (DataIntegrityViolationException e) {
            throw new EntityIntegrityViolationException(
                    "Erro ao desvincular imagens do produto: " +
                            imagem.toString()+ " "+
                            e.getMessage()
            );
        }
    }

    public void deletarImagem(int idImagem) {
        try {
            imagemRepository.deletarImagem(idImagem);
        } catch ( DataIntegrityViolationException  dataException) {
            throw new EntityIntegrityViolationException(
                    "Imagem utilizada em cadastros do sistema: "+ idImagem);
        }
    }

    public void vincularImagemProduto(ImagemProdutoDTO imagemProdutoDTO) {
        try{
            imagemRepository.vincularImagemProduto(imagemProdutoDTO);
        }catch(DataIntegrityViolationException e ){
            throw new EntityIntegrityViolationException(
                    "Imagem ou Produto não cadastrado na base: " + imagemProdutoDTO.toString());
        }
    }

    public int getCodImagemProdutoCadastrada(){
        return imagemRepository.getCodImagemCadastrada();
    }

    public Imagem converteMultipartFileParaImagem(MultipartFile arqImagem) {
        Optional<MultipartFile> optImg = Optional.ofNullable(arqImagem);
        if (optImg.isPresent()){
            try {
                return new Imagem( StringUtils.cleanPath(arqImagem.getOriginalFilename()),
                        arqImagem.getContentType(), arqImagem.getBytes());
            }catch( IOException ioException){
                throw new FileHandleException(
                        "Erro no tratamento da imagem:" + arqImagem.toString());
            }
        }else{
            throw new FileHandleException("Imagem não enviada na requisição!");
        }
    }


    public MultipartFile converteImagemParaMultipartFile(Imagem imagem) {

      return null;
    }

}


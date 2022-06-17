package br.com.univesp.mercadocell.mercadocell.service;

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

    public void cadastrarImagem(MultipartFile arqImagem) {
        Imagem img = null;
        Optional<MultipartFile> optImg = Optional.ofNullable(arqImagem);
        if (optImg.isPresent()){
            try {
                img = converteMultipartFileParaImagem(arqImagem);
                imagemRepository.buscarImagemPorNome(img.getNomeImagem());
            }catch( IOException ioException){
                throw new FileHandleException(
                        "Erro no tratamento da imagem:" + img.toString());
            }catch(EmptyResultDataAccessException emptyResultDataAccessException){
                imagemRepository.cadastrarImagem(img);
            }
        }else{
            throw new FileHandleException("Imagem não enviada na requisição!");
        }
    }

    public void atualizarImagem(MultipartFile arqImagem) {
        Imagem img = null;
        Optional<MultipartFile> optImg = Optional.ofNullable(arqImagem);
        if (optImg.isPresent()){
            try {
                img = converteMultipartFileParaImagem(arqImagem);
                imagemRepository.atualizarImagem(img);
            }catch( IOException ioException) {
                throw new FileHandleException(
                        "Erro no tratamento da imagem:" + img.toString());
            }
        } else{
        throw new FileHandleException("Imagem não enviada na requisição!");
        }
    }

    public Imagem buscarImagemPorId(int  idImagem) {
        try {
            return imagemRepository.buscarImagemPorId(idImagem);
        }catch(EmptyResultDataAccessException e) {
            throw  new EntityNotFoundException("Nenhuma imagem foi encontrada com o id: " + idImagem);
        }
    }


    public static Imagem converteMultipartFileParaImagem(MultipartFile imagem) throws IOException {
        return new Imagem( StringUtils.cleanPath(imagem.getOriginalFilename()),
                imagem.getContentType(), imagem.getBytes());
    }


    public List<Imagem> buscarImagemProdutoPorId(int  idProduto) {
        try {
            return imagemRepository.buscarImagemProdutoPorId(idProduto);
        }catch(EmptyResultDataAccessException e) {
            throw  new EntityNotFoundException("Nenhuma imagem fioi encontrada para o produto: " + idProduto);
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
}


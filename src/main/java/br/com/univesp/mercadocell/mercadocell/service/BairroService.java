package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Bairro;
import br.com.univesp.mercadocell.mercadocell.repository.BairroRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BairroService {

    @Autowired
    private BairroRepository bairroRepository;

    public void cadastrarBairro(Bairro bairro) {
        try {
            bairroRepository.buscarBairroPorNome(bairro.getNomeBairro());
            throw new EntityIntegrityViolationException("Bairro já cadastrado: " + bairro.toString());
        }catch (EmptyResultDataAccessException e){
            try {
                bairroRepository.cadastrarBairro(bairro);
            }catch (DataIntegrityViolationException dataIntegrityViolationException) {
                throw new EntityIntegrityViolationException(
                        "Dados de Bairro Inconsistentes:" + bairro.toString());
            }
        }
    }

    public Bairro buscarBairroPorId(int idBairro) {
        try{
            return  bairroRepository.buscarBairroPorId(idBairro);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Código de Bairro não encontrado: " + idBairro);
        }
    }

    public List<Bairro> listarBairros() {
        try{
            return bairroRepository.listarBairros();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException("Nenhum registro encontrado");
        }
    }

    public void atualizarBairro(Bairro bairro) {
        try {
            bairroRepository.atualizarBairro(bairro);
        }catch(DataIntegrityViolationException e){
            throw  new EntityIntegrityViolationException(
                "O Municipio informado não foi cadastrado na base: " +  bairro.getCodMunicipio());
        }
    }

    public void deletarBairro(int idBairro) {
        try {
            bairroRepository.deletarBairro(idBairro);
        } catch (DataIntegrityViolationException e) {
            throw new EntityIntegrityViolationException(
                    "Bairro utilizado no cadastro de logradouros: : " + idBairro
            );
        }
    }

}

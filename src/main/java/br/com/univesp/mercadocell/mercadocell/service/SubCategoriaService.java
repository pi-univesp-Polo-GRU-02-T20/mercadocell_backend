package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.SubCategoria;
import br.com.univesp.mercadocell.mercadocell.repository.SubCategoriaRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoriaService {

    @Autowired
    private SubCategoriaRepository subCategoriaRepository;

    public void cadastrarSubCategoria(SubCategoria subCategoria) {
        try {
            subCategoriaRepository.buscarSubCategoriaPorNome(subCategoria.getNomeSubCategoria());
            throw new EntityIntegrityViolationException("Subcategoria já cadastrada");
        }catch (EmptyResultDataAccessException e) {
            try {
                subCategoriaRepository.cadastrarSubCategoria(subCategoria);
            } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                throw new EntityIntegrityViolationException(
                        "A Categoria informada não foi cadastrada na base: " + subCategoria.getCategoria().toString());
            }
        }
    }

    public SubCategoria buscarSubCategoriaPorId(int idSubCategoria) {
        try{
            return subCategoriaRepository.buscarSubCategoriaPorId(idSubCategoria);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Código de subCategoria não encontrado: =" + idSubCategoria
            );
        }
    }

    public List<SubCategoria> listarSubCategorias() {
        try{
            return subCategoriaRepository.listarSubCategorias();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException("Nenhum registro encontrado");
        }
    }

    public void atualizarSubCategoria(SubCategoria subCategoria) {
        try{
            subCategoriaRepository.atualizarSubCategoria(subCategoria);
        }catch(DataIntegrityViolationException e ){
            throw new EntityIntegrityViolationException(
                    "A Categoria informada não foi cadastrada na base: " + subCategoria.getCategoria().toString());
        }
    }

    public void deletarSubCategoria(int idSubCategoria) {
        try {
            subCategoriaRepository.deletarSubCategoria(idSubCategoria);
        } catch (DataIntegrityViolationException e) {
            throw new EntityIntegrityViolationException(
                    "Subcategoria utilizada no cadastro de produtos: " + idSubCategoria
            );
        }
    }
}

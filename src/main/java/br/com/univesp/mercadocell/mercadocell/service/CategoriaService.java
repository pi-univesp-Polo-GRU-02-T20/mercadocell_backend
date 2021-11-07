package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import br.com.univesp.mercadocell.mercadocell.repository.CategoriaRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void cadastrarCategoria(Categoria categoria) {
        try {
            categoriaRepository.buscarCategoriaPorNome(categoria.getNomeCategoria());
            throw new EntityIntegrityViolationException("Categoria já cadastrada: " + categoria.toString());
        }catch (EmptyResultDataAccessException e){
            categoriaRepository.cadastrarCategoria(categoria);
        }
    }

    public Categoria buscarCategoriaPorId(int idCategoria) {
        try{
            return categoriaRepository.buscarCategoriaPorId(idCategoria);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException("Código de categoria não encontrada: " + idCategoria);
        }
    }

    public List<Categoria> listarCategorias() {
        try{
            return categoriaRepository.listarCategorias();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException("Nenhum registro encontrado");
        }
    }

    public void atualizarCategoria(Categoria categoria) {
        categoriaRepository.atualizarCategoria(categoria);
    }

    public void deletarCategoria(int idCategoria) {
        try {
            categoriaRepository.deletarCategoria(idCategoria);
        } catch ( DataIntegrityViolationException  dataException) {
            throw new EntityIntegrityViolationException(
                    "Categoria utilizada no cadastro de subcategorias: "+ idCategoria);
        }
    }

}

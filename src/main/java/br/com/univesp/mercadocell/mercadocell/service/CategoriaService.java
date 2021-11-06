package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import br.com.univesp.mercadocell.mercadocell.repository.CategoriaRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void cadastrarCategoria(Categoria categoria) {
        Optional<Categoria> categoriaBuscaOpt  =
                Optional.ofNullable(categoriaRepository.buscarCategoriaPorNome(categoria.getNomeCategoria()));
        if (categoriaBuscaOpt.isEmpty()){
            categoriaRepository.cadastrarCategoria(categoria);
        } else {
            throw new EntityIntegrityViolationException("Categoria já cadastrada");
        }
    }

    public Categoria buscarCategoriaPorId(int idCategoria) {
        return categoriaRepository.buscarCategoriaPorId(idCategoria);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.listarCategorias();
    }

    public void atualizarCategoria(Categoria categoria) {
        categoriaRepository.atualizarCategoria(categoria);
    }

    public void deletarCategoria(int idCategoria) {
        categoriaRepository.deletarCategoria(idCategoria);
    }

}

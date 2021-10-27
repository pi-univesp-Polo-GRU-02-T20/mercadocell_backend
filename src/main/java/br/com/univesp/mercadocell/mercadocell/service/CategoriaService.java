package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import br.com.univesp.mercadocell.mercadocell.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void cadastrarCategoria(Categoria categoria) {
        categoriaRepository.cadastrarCategoria(categoria);
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

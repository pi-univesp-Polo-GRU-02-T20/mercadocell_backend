package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Categoria;
import br.com.univesp.mercadocell.mercadocell.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public void cadastrarCategoria(Categoria categoria) {
        repository.cadastrarCategoria(categoria);
    }

    public Categoria buscarCategoriaPorId(int idCategoria) {
        return repository.buscarCategoriaPorId(idCategoria);
    }

    public List<Categoria> listarCategorias() {
        return repository.listarCategorias();
    }

    public void atualizarCategoria(Categoria categoria) {
        repository.atualizarCategoria(categoria);
    }

    public void deletarCategoria(int idCategoria) {
        repository.deletarCategoria(idCategoria);
    }

}

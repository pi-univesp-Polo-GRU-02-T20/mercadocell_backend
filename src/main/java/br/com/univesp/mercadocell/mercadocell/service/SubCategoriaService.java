package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.SubCategoria;
import br.com.univesp.mercadocell.mercadocell.repository.SubCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoriaService {

    @Autowired
    private SubCategoriaRepository repository;

    public void cadastrarSubCategoria(SubCategoria subCategoria) {
        repository.cadastrarSubCategoria(subCategoria);
    }

    public SubCategoria buscarSubCategoriaPorId(int idSubCategoria) {
        return repository.buscarSubCategoriaPorId(idSubCategoria);
    }

    public List<SubCategoria> listarSubCategorias() {
        return repository.listarSubCategorias();
    }

    public void atualizarSubCategoria(SubCategoria subCategoria) {
        repository.atualizarSubCategoria(subCategoria);
    }

    public void deletarSubCategoria(int idSubCategoria) {
        repository.deletarSubCategoria(idSubCategoria);
    }

}

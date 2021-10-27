package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.SubCategoria;
import br.com.univesp.mercadocell.mercadocell.repository.CategoriaRepository;
import br.com.univesp.mercadocell.mercadocell.repository.SubCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoriaService {

    @Autowired
    private SubCategoriaRepository subCategoriaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public void cadastrarSubCategoria(SubCategoria subCategoria) {
        subCategoriaRepository.cadastrarSubCategoria(subCategoria, categoriaRepository);
    }

    public SubCategoria buscarSubCategoriaPorId(int idSubCategoria) {
        return subCategoriaRepository.buscarSubCategoriaPorId(idSubCategoria);
    }

    public List<SubCategoria> listarSubCategorias() {
        return subCategoriaRepository.listarSubCategorias();
    }

    public void atualizarSubCategoria(SubCategoria subCategoria) {
        subCategoriaRepository.atualizarSubCategoria(subCategoria);
    }

    public void deletarSubCategoria(int idSubCategoria) {
        subCategoriaRepository.deletarSubCategoria(idSubCategoria);
    }

}

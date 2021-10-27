package br.com.univesp.mercadocell.mercadocell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.univesp.mercadocell.mercadocell.model.Estado;
import br.com.univesp.mercadocell.mercadocell.repository.EstadoRepository;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public void cadastrarEstado(Estado estado) {
        repository.cadastrarEstado(estado);
    }

    public Object buscarEstadoPorId(int idEstado) {
        return null;
    }

    public List<Estado> listarEstados() {
        return repository.listarEstados();
    }
    
    public void atualizarEstados(Estado estado) {
        repository.atualizarEstados(estado);
    }

    public void deletarEstado(int idEstado) {
        repository.deletarEstado(idEstado);
    }

}

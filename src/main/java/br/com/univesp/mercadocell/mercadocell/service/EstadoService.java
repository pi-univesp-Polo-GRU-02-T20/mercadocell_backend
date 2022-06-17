package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Estado;
import br.com.univesp.mercadocell.mercadocell.repository.EstadoRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public void cadastrarEstado(Estado estado) {
        try{
            estadoRepository.buscarEstadoPorUF(estado.getSiglaUF());
            throw new EntityIntegrityViolationException("Estado já cadastrado: " + estado.toString());
        } catch(EmptyResultDataAccessException e){
            estadoRepository.cadastrarEstado(estado);
        }
    }

    public Estado  buscarEstadoPorId(int idEstado) {
        try {
            return estadoRepository.buscarEstadoPorId(idEstado);
        }catch(EmptyResultDataAccessException e){
            throw new EntityNotFoundException("Código de Estado não encontrado");
        }
    }

    public List<Estado> listarEstados() {
        try {
            return estadoRepository.listarEstados();
        }catch(EmptyResultDataAccessException e){
            throw new EntityNotFoundException("Nenhum registro encontrado");
        }
    }
    
    public void atualizarEstado(Estado estado) {
        estadoRepository.atualizarEstado(estado);
    }

    public void deletarEstado(int idEstado) {
        try{
            estadoRepository.deletarEstado(idEstado);
        }catch(DataIntegrityViolationException dataException){
            throw new EntityIntegrityViolationException("Estado utilizado no cadastro de municípios: "
                    + idEstado);
        }
    }
}

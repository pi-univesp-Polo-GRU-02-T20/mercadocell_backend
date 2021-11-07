package br.com.univesp.mercadocell.mercadocell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.univesp.mercadocell.mercadocell.model.Estado;
import br.com.univesp.mercadocell.mercadocell.repository.EstadoRepository;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    //TODO ADD VERIFICAÇÃO DE ESTADO EXISTENTE NA BASE ANTES DE CADASTRAR
    public void cadastrarEstado(Estado estado) {
        estadoRepository.cadastrarEstado(estado);
    }

    public Estado  buscarEstadoPorId(int idEstado) {
        return estadoRepository.buscarEstadoPorId(idEstado);
    }

    public List<Estado> listarEstados() {
        return estadoRepository.listarEstados();
    }
    
    public void atualizarEstado(Estado estado) {
        estadoRepository.atualizarEstados(estado);
    }

    // TODO ADD VERIFICAÇÃO DE ERRO DE FK (MUNICIPIO VINCULADO AO ESTADO A SER APAGADO)
    public void deletarEstado(int idEstado) {
        estadoRepository.deletarEstado(idEstado);
    }
}

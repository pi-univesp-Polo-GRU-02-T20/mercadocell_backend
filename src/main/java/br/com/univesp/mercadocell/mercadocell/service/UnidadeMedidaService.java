package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.UnidadeMedida;
import br.com.univesp.mercadocell.mercadocell.repository.UnidadeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UnidadeMedidaService {

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    public void cadastrarUnidadeMedida(UnidadeMedida unidadeMedida) {
        unidadeMedidaRepository.cadastrarUnidadeMedida(unidadeMedida);
    }

    public UnidadeMedida buscarUnidadeMedidaPorId(int idUnidadeMedida) {
        return unidadeMedidaRepository.buscarUnidadeMedidaPorId(idUnidadeMedida);
    }

    public List<UnidadeMedida> listarUnidadesMedida() {
        return unidadeMedidaRepository.listarUnidadeMedida();
    }

    public void atualizarUnidadeMedida(UnidadeMedida tipoPagamento) {
        unidadeMedidaRepository.atualizarUnidadeMedida(tipoPagamento);
    }

    public void deletarUnidadeMedida(int idUnidadeMedida) {
        unidadeMedidaRepository.deletarUnidadeMedida(idUnidadeMedida);
    }
}

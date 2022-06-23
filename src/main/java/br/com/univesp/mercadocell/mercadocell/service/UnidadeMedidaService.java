package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.UnidadeMedida;
import br.com.univesp.mercadocell.mercadocell.repository.UnidadeMedidaRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UnidadeMedidaService {

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    public void cadastrarUnidadeMedida(UnidadeMedida unidadeMedida) {
        try {
            unidadeMedidaRepository.buscarUnidadeMedidaPorNome(unidadeMedida.getNomeUnidadeMedida());
            throw new EntityIntegrityViolationException("Categoria já cadastrada: " + unidadeMedida.toString());
        }catch (EmptyResultDataAccessException e){
            try {
                unidadeMedidaRepository.cadastrarUnidadeMedida(unidadeMedida);
            } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                throw new EntityIntegrityViolationException(
                        "Dados de Unidade de Medida inconsistentes: " + unidadeMedida.toString());
            }
        }

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

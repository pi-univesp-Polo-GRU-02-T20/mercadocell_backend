package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.UnidadeMedida;
import br.com.univesp.mercadocell.mercadocell.repository.UnidadeMedidaRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UnidadeMedidaService {

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    //TODO ADD VERIFICAÇÃO DE UNID MED EXISTENTE NA BASE ANTES DE CADASTRAR
    public void cadastrarUnidadeMedida(UnidadeMedida unidadeMedida) {
        try {
            unidadeMedidaRepository.buscarUnidadeMedidaPorNome(unidadeMedida.getNomeUnidadeMedida());
            throw new EntityIntegrityViolationException("Categoria já cadastrada: " + unidadeMedida.toString());
        }catch (EmptyResultDataAccessException e){
            unidadeMedidaRepository.cadastrarUnidadeMedida(unidadeMedida);
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
    // TODO ADD VERIFICAÇÃO DE ERRO DE FK (PRODUTO VINCULADO A UNID MED A SER APAGADA)
    public void deletarUnidadeMedida(int idUnidadeMedida) {
        unidadeMedidaRepository.deletarUnidadeMedida(idUnidadeMedida);
    }
}

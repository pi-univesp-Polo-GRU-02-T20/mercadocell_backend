package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoDTO;
import br.com.univesp.mercadocell.mercadocell.repository.RelatorioRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;
    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamento() {
        //relatorioRepository = new RelatorioRepository();
        try{
            return relatorioRepository.carregarRelatorioFaturamento();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException("Nenhum registro encontrado");
        }
    }

}

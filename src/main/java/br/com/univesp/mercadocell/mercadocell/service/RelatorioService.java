package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoSMO;
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
    private String anoFaturamento;

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoDiario() {
        //relatorioRepository = new RelatorioRepository();
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoDiario();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException("Nenhum registro encontrado");
        }
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoMensal(String mesAnoFaturamento) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoMensal(mesAnoFaturamento);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "O faturamento referente ao mês-ano " +mesAnoFaturamento + ", não foi encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoAnual(String anoFaturamento) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoAnual(anoFaturamento);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "O faturamento referente ao ano  "
                            + anoFaturamento + ", não foi encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoDiario() {
        //relatorioRepository = new RelatorioRepository();
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoDiario();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException("Nenhum registro encontrado");
        }
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoMensal(String mesAnoFaturamento) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoMensal(mesAnoFaturamento);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "O faturamento referente ao mês-ano " +mesAnoFaturamento + ", não foi encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoAnual(String anoFaturamento) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoAnual(anoFaturamento);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "O faturamento referente ao ano  "
                            + anoFaturamento + ", não foi encontrado."
            );
        }
    }

}

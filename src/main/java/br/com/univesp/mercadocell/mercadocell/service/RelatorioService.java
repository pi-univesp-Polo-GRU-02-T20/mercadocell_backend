package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoSMO;
import br.com.univesp.mercadocell.mercadocell.repository.RelatorioRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;
    private String anoFaturamento;

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoDiario() {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoDiario();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Diário não encontrado"
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoDiarioPeriodo
            (LocalDate dataInicio, LocalDate dataTermino) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoDiarioPeriodo(dataInicio, dataTermino);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Diário por Período não encontrado"
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoMensalNull() {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoMensalNull();
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Diário não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoMensal(String anoMesFaturamento) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoMensal(anoMesFaturamento);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Mensal não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoMensalPeriodo
            (String anoMesInicio, String anoMesTermino) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoMensalPeriodo(anoMesInicio, anoMesTermino);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Mensal por Período não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoAnualNull() {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoAnualNull();
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Mensal não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoAnual(String anoFaturamento) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoAnual(anoFaturamento);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Anual não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDTO> carregarRelatorioFaturamentoDetalhadoAnualPeriodo
            (String anoInicial, String anoTermino) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoAnualPeriodo(anoInicial, anoTermino);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Anual por Período não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoDiario() {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoDiario();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Diário não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoDiarioPeriodo
            (LocalDate dataInicio, LocalDate dataTermino) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoDiarioPeriodo(dataInicio, dataTermino);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Diário por Período não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoMensalNull() {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoMensalNull();
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Diário não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoMensal(String anoMesFaturamento) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoMensal(anoMesFaturamento);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Mensal não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoMensalPeriodo
            (String anoMesInicial, String anoMesTermino) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoMensalPeriodo(anoMesInicial, anoMesTermino);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Mensal por Período não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoAnualNull() {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoAnualNull();
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Anual não encontrado."
            );
        }
    }


    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoAnual(String anoFaturamento) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoAnual(anoFaturamento);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Anual não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSMO> carregarRelatorioFaturamentoSumarizadoAnualPeriodo
            (String anoInicial, String anoTermino) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoAnualPeriodo(anoInicial, anoTermino);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Anual por Período não encontrado."
            );
        }
    }
}

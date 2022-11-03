package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoDetalhadoDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ProdutoRelatorioFaturamentoSumarizadoDTO;
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

    public List<ProdutoRelatorioFaturamentoSumarizadoDTO> carregarRelatorioFaturamentoDetalhadoDiario() {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoDiario();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Diário não encontrado"
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSumarizadoDTO> carregarRelatorioFaturamentoDetalhadoDiarioPeriodo
            (LocalDate dataInicio, LocalDate dataTermino) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoDiarioPeriodo(dataInicio, dataTermino);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Diário por Período não encontrado"
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSumarizadoDTO> carregarRelatorioFaturamentoDetalhadoMensalNull() {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoMensalNull();
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Diário não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSumarizadoDTO> carregarRelatorioFaturamentoDetalhadoMensal(String anoMesFaturamento) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoMensal(anoMesFaturamento);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Mensal não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSumarizadoDTO> carregarRelatorioFaturamentoDetalhadoMensalPeriodo
            (String anoMesInicio, String anoMesTermino) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoMensalPeriodo(anoMesInicio, anoMesTermino);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Mensal por Período não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSumarizadoDTO> carregarRelatorioFaturamentoDetalhadoAnualNull() {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoAnualNull();
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Mensal não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSumarizadoDTO> carregarRelatorioFaturamentoDetalhadoAnual(String anoFaturamento) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoAnual(anoFaturamento);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Anual não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoSumarizadoDTO> carregarRelatorioFaturamentoDetalhadoAnualPeriodo
            (String anoInicial, String anoTermino) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoDetalhadoAnualPeriodo(anoInicial, anoTermino);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Detalhado Anual por Período não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDetalhadoDTO> carregarRelatorioFaturamentoSumarizadoDiario() {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoDiario();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Diário não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDetalhadoDTO> carregarRelatorioFaturamentoSumarizadoDiarioPeriodo
            (LocalDate dataInicio, LocalDate dataTermino) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoDiarioPeriodo(dataInicio, dataTermino);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Diário por Período não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDetalhadoDTO> carregarRelatorioFaturamentoSumarizadoMensalNull() {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoMensalNull();
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Diário não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDetalhadoDTO> carregarRelatorioFaturamentoSumarizadoMensal(String anoMesFaturamento) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoMensal(anoMesFaturamento);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Mensal não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDetalhadoDTO> carregarRelatorioFaturamentoSumarizadoMensalPeriodo
            (String anoMesInicial, String anoMesTermino) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoMensalPeriodo(anoMesInicial, anoMesTermino);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Mensal por Período não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDetalhadoDTO> carregarRelatorioFaturamentoSumarizadoAnualNull() {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoAnualNull();
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Anual não encontrado."
            );
        }
    }


    public List<ProdutoRelatorioFaturamentoDetalhadoDTO> carregarRelatorioFaturamentoSumarizadoAnual(String anoFaturamento) {
        try{
            return relatorioRepository.carregarRelatorioFaturamentoSumarizadoAnual(anoFaturamento);
        } catch(EmptyResultDataAccessException e){
            throw  new EntityNotFoundException(
                    "Faturamento Sumarizado Anual não encontrado."
            );
        }
    }

    public List<ProdutoRelatorioFaturamentoDetalhadoDTO> carregarRelatorioFaturamentoSumarizadoAnualPeriodo
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

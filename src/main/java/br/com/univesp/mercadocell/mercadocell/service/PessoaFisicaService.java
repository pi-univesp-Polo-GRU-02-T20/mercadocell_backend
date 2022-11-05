package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.PessoaFisica;
import br.com.univesp.mercadocell.mercadocell.repository.PessoaFisicaRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;
    private EnderecoService enderecoService;

    public void cadastrarPessoaFisica(PessoaFisica pessoaFisica) {
        try {
            pessoaFisicaRepository.buscarPessoaFisicaPorNome(pessoaFisica.getNomePessoa());
            throw new EntityIntegrityViolationException("Pessoa física já cadastrada");
        }catch (EmptyResultDataAccessException e) {
                try{
                    pessoaFisicaRepository.cadastrarPessoaFisica(pessoaFisica);
                    enderecoService.cadastrarEndereco(pessoaFisica.getEndereco());
                }catch (DataIntegrityViolationException dataIntegrityViolationException) {
                    throw new EntityIntegrityViolationException(
                            "Dados de Pessoa Física inconsistentes:" + pessoaFisica.toString());
                }
        }
    }

    public PessoaFisica buscarPessoaFisicaPorId(int idPessoaFisica) {
        try{
            PessoaFisica pessoa = pessoaFisicaRepository.buscarPessoaFisicaPorId(idPessoaFisica);
            pessoa.setEndereco(enderecoService.buscarEnderecoPorCodPessoa(pessoa.getCodPessoa()));
            return pessoa;
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Código de Pessoa Física não encontrado: " + idPessoaFisica
            );
        }
    }

    public PessoaFisica buscarPessoaFisicaPorNome(String nomePessoaFisica) {
        try{
            PessoaFisica pessoa = pessoaFisicaRepository.buscarPessoaFisicaPorNome(nomePessoaFisica);
            pessoa.setEndereco(enderecoService.buscarEnderecoPorCodPessoa(pessoa.getCodPessoa()));
            return pessoa;
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Nome de Pessoa Física não encontrado: " + nomePessoaFisica
            );
        }
    }

    public List<PessoaFisica> listarPessoasFisicas() {
        try{
            return pessoaFisicaRepository.listarPessoasFisicas();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException("Nenhum registro encontrado");
        }
    }

    public void atualizarPessoaFisica(PessoaFisica pessoaFisica) {
        pessoaFisicaRepository.atualizarPessoaFisica(pessoaFisica);
    }

    public void deletarPessoaFisica(int idPessoaFisica) {
        try {
            pessoaFisicaRepository.deletarPessoaFisica(idPessoaFisica);
        } catch (DataIntegrityViolationException e) {
            throw new EntityIntegrityViolationException(
                    "Pessoa física utilizada em cadastros do sistema: " + idPessoaFisica
            );
        }
    }
}

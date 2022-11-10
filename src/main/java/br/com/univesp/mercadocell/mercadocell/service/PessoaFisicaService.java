package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.PessoaFisica;
import br.com.univesp.mercadocell.mercadocell.repository.PessoaFisicaRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;
    @Autowired
    private EnderecoService enderecoService;

    @Transactional
    public void cadastrarPessoaFisica(PessoaFisica pessoaFisica) {
        try {
            pessoaFisicaRepository.buscarPessoaFisicaPorNome(pessoaFisica.getNomePessoa());
            throw new EntityIntegrityViolationException("Pessoa física já cadastrada");
        }catch (EmptyResultDataAccessException e) {
                try{
                    int codPessoaFisica = pessoaFisicaRepository.cadastrarPessoaFisica(pessoaFisica);
                    pessoaFisica.setCodPessoa(codPessoaFisica);
                    pessoaFisica.getEndereco().setCodPessoa(codPessoaFisica);
                    try{
                        enderecoService.cadastrarEndereco(pessoaFisica.getEndereco());
                    }catch (DataIntegrityViolationException dataIntegrityViolationException) {
                        throw new EntityIntegrityViolationException(
                                "Dados de Endereço da pessoa Física inconsistentes:" + pessoaFisica.toString());
                    }
                }catch (DataIntegrityViolationException dataIntegrityViolationException) {
                    throw new EntityIntegrityViolationException(
                            "Dados de Pessoa Física inconsistentes:" + pessoaFisica.toString());
                }
        }
    }

    public PessoaFisica buscarPessoaFisicaPorId(int idPessoaFisica) {
        try{
            PessoaFisica pessoa = pessoaFisicaRepository.buscarPessoaFisicaPorId(idPessoaFisica);
            try{
                pessoa.setEndereco(enderecoService.buscarEnderecoPorCodPessoa(pessoa.getCodPessoa()));
            }catch (EmptyResultDataAccessException e ){
                // se a pessoa não tiver endereço cadastrado, retorna os dados somente da pessoa
            }
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
    @Transactional
    public void atualizarPessoaFisica(PessoaFisica pessoaFisica) {
        pessoaFisicaRepository.atualizarPessoaFisica(pessoaFisica);
    }

    @Transactional
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

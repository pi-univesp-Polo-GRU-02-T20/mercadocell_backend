package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.PessoaFisica;
import br.com.univesp.mercadocell.mercadocell.model.PessoaJuridica;
import br.com.univesp.mercadocell.mercadocell.repository.PessoaJuridicaRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;
    private EnderecoService enderecoService;

    public void cadastrarPessoaJuridica(PessoaJuridica pessoaJuridica) {
        try {
            System.out.println(pessoaJuridica.toString());
            pessoaJuridicaRepository.buscarPessoaJuridicaPorNome(pessoaJuridica.getNomePessoa());
            throw new EntityIntegrityViolationException("Pessoa jurídica já cadastrada");
        }catch (EmptyResultDataAccessException e) {
            try{
                pessoaJuridicaRepository.cadastrarPessoaJuridica(pessoaJuridica);
                enderecoService.cadastrarEndereco(pessoaJuridica.getEndereco());
            }catch (DataIntegrityViolationException dataIntegrityViolationException) {
                throw new EntityIntegrityViolationException(
                        "Dados de Pessoa Jurídica inconsistentes:" + pessoaJuridica.toString());
            }
        }
    }

    public PessoaJuridica buscarPessoaJuridicaPorId(int idPessoaJuridica) {
        try{
            PessoaJuridica pessoa = pessoaJuridicaRepository.buscarPessoaJuridicaPorId(idPessoaJuridica);
            pessoa.setEndereco(enderecoService.buscarEnderecoPorCodPessoa(pessoa.getCodPessoa()));
            return pessoa;
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Código de Pessoa Jurídica não encontrado: " + idPessoaJuridica
            );
        }
    }

    public PessoaJuridica buscarPessoaJuridicaPorNome(String nomePessoaJuridica) {
        try{
            PessoaJuridica pessoa = pessoaJuridicaRepository.buscarPessoaJuridicaPorNome(nomePessoaJuridica);
            return pessoa;
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Nome de Pessoa Jurídica não encontrado: " + nomePessoaJuridica
            );
        }
    }
    public List<PessoaJuridica> listarPessoasJuridicas() {
        try{
            return pessoaJuridicaRepository.listarPessoasJuridicas();
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException("Nenhum registro encontrado");
        }
    }

    public void atualizarPessoaJuridica(PessoaJuridica pessoaJuridica) {
        pessoaJuridicaRepository.atualizarPessoaJuridica(pessoaJuridica);
    }

    public void deletarPessoaJuridica(int idPessoaJuridica) {
        try {
            pessoaJuridicaRepository.deletarPessoaJuridica(idPessoaJuridica);
        } catch (DataIntegrityViolationException e) {
            throw new EntityIntegrityViolationException(
                    "Pessoa jurídica utilizada em cadastros do sistema: " + idPessoaJuridica
            );
        }
    }
}

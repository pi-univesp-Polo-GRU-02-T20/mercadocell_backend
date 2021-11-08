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

    public void cadastrarPessoaJuridica(PessoaJuridica pessoaJuridica) {
        try {
            pessoaJuridicaRepository.buscarPessoaJuridicaPorNome(pessoaJuridica.getNomePessoa());
            throw new EntityIntegrityViolationException("Pessoa jurídica já cadastrada" + pessoaJuridica.toString());
        }catch (EmptyResultDataAccessException e) {
            pessoaJuridicaRepository.cadastrarPessoaJuridica(pessoaJuridica);
        }
    }

    public PessoaJuridica buscarPessoaJuridicaPorId(int idPessoaJuridica) {
        try{
            return pessoaJuridicaRepository.buscarPessoaJuridicaPorId(idPessoaJuridica);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Código de Pessoa Jurídica não encontrado: " + idPessoaJuridica
            );
        }
    }

    public PessoaJuridica buscarPessoaJuridicaPorNome(String nomePessoaJuridica) {
        try{
            return pessoaJuridicaRepository.buscarPessoaJuridicaPorNome(nomePessoaJuridica);
        }catch (EmptyResultDataAccessException e ){
            throw  new EntityNotFoundException(
                    "Nome de Pessoa Física não encontrado: " + nomePessoaJuridica
            );
        }
    }
    public List<PessoaJuridica> listarPessoasJuridicas() {
        return pessoaJuridicaRepository.listarPessoasJuridicas();
    }

    public void atualizarPessoaJuridica(PessoaJuridica PessoaJuridica) {
        pessoaJuridicaRepository.atualizarPessoaJuridica(PessoaJuridica);
    }

    public void deletarPessoaJuridica(int idPessoaJuridica) {
        try {
            pessoaJuridicaRepository.deletarPessoaJuridica(idPessoaJuridica);
        } catch (DataIntegrityViolationException e) {
            throw new EntityIntegrityViolationException(
                    "Pessoa física utilizada em cadastros do sistema: " + idPessoaJuridica
            );
        }
    }
}

package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.PessoaFisica;
import br.com.univesp.mercadocell.mercadocell.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    public void cadastrarPessoaFisica(PessoaFisica PessoaFisica) {
        pessoaFisicaRepository.cadastrarPessoaFisica(PessoaFisica);
    }

    public PessoaFisica buscarPessoaFisicaPorId(int idPessoaFisica) {
        return pessoaFisicaRepository.buscarPessoaFisicaPorId(idPessoaFisica);
    }
    public PessoaFisica buscarPessoaFisicaPorNome(String nomePessoaFisica) {
        return pessoaFisicaRepository.buscarPessoaFisicaPorNome(nomePessoaFisica);
    }
    public List<PessoaFisica> listarPessoasFisicas() {
        return pessoaFisicaRepository.listarPessoasFisicas();
    }

    public void atualizarPessoaFisica(PessoaFisica PessoaFisica) {
        pessoaFisicaRepository.atualizarPessoaFisica(PessoaFisica);
    }

    public void deletarPessoaFisica(int idPessoaFisica) {
        pessoaFisicaRepository.deletarPessoaFisica(idPessoaFisica);
    }


}

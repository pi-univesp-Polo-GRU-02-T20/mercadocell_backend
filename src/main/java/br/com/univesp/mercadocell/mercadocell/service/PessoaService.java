package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Pessoa;
import br.com.univesp.mercadocell.mercadocell.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public void cadastrarPessoa(Pessoa pessoa) {
        pessoaRepository.cadastrarPessoa(pessoa);
    }

    public Pessoa buscarPessoaPorId(int  idPessoa) {
        return pessoaRepository.buscarPessoaPorId(idPessoa);
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.listarPessoas();
    }

    public void atualizarPessoas(Pessoa pessoa) {
        pessoaRepository.atualizarPessoa(pessoa);
    }

    public void deletarPessoa(int idPessoa) {
        pessoaRepository.deletarPessoa(idPessoa);
    }

}

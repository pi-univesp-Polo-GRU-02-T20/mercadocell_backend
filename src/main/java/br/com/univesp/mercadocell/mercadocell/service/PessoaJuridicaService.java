package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.PessoaFisica;
import br.com.univesp.mercadocell.mercadocell.model.PessoaJuridica;
import br.com.univesp.mercadocell.mercadocell.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public void cadastrarPessoaJuridica(PessoaJuridica PessoaJuridica) {
        pessoaJuridicaRepository.cadastrarPessoaJuridica(PessoaJuridica);
    }

    public PessoaJuridica buscarPessoaJuridicaPorId(int idPessoaJuridica) {
        return pessoaJuridicaRepository.buscarPessoaJuridicaPorId(idPessoaJuridica);
    }
    public PessoaJuridica buscarPessoaJuridicaPorNome(String nomePessoaJuridica) {
        return pessoaJuridicaRepository.buscarPessoaJuridicaPorNome(nomePessoaJuridica);
    }
    public List<PessoaJuridica> listarPessoasJuridicas() {
        return pessoaJuridicaRepository.listarPessoasJuridicas();
    }

    public void atualizarPessoaJuridica(PessoaJuridica PessoaJuridica) {
        pessoaJuridicaRepository.atualizarPessoaJuridica(PessoaJuridica);
    }

    public void deletarPessoaJuridica(int idPessoaJuridica) {
        pessoaJuridicaRepository.deletarPessoaJuridica(idPessoaJuridica);
    }
}

package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Endereco;
import br.com.univesp.mercadocell.mercadocell.model.Logradouro;
import br.com.univesp.mercadocell.mercadocell.repository.EnderecoRepository;
import br.com.univesp.mercadocell.mercadocell.repository.LogradouroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public void cadastrarEndereco(Endereco endereco) {
        enderecoRepository.cadastrarEndereco(endereco);
    }

    public Endereco  buscarEnderecoPorId(int codEndereco) {
        return enderecoRepository.buscarEnderecoPorId(codEndereco);
    }

    public Endereco  buscarEnderecoPorCodPessoa(int codPessoa) {
        return enderecoRepository.buscarEnderecoPorCodPessoa(codPessoa);
    }

    public List<Endereco> listarEnderecos() {
        return enderecoRepository.listarEnderecos();
    }

    public void atualizarEndereco(Endereco endereco) {
        enderecoRepository.atualizarEndereco(endereco);
    }

    public void deletarLogradouro(int idLogradouro) {
        enderecoRepository.deletarEndereco(idLogradouro);
    }
}

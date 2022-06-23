package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Logradouro;
import br.com.univesp.mercadocell.mercadocell.repository.LogradouroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogradouroService {

    @Autowired
    private LogradouroRepository logradouroRepository;
    //TODO ADD VALIDAÇÕES
    public void cadastrarLogradouro(Logradouro logradouro) {
        logradouroRepository.cadastrarLogradouro(logradouro);
    }

    public Logradouro  buscarLogradouroPorId(int idLogradouro) {
        return logradouroRepository.buscarLogradouroPorId(idLogradouro);
    }

    public List<Logradouro> listarLogradouros() {
        return logradouroRepository.listarLogradouros();
    }
    
    public void atualizarLogradouro(Logradouro logradouro) {
        logradouroRepository.atualizarLogradouro(logradouro);
    }

    public void deletarLogradouro(int idLogradouro) {
        logradouroRepository.deletarLogradouro(idLogradouro);
    }
}

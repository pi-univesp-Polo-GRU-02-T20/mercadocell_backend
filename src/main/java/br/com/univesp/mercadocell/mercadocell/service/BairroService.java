package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Bairro;
import br.com.univesp.mercadocell.mercadocell.repository.BairroRepository;
import br.com.univesp.mercadocell.mercadocell.service.exception.EntityIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BairroService {

    @Autowired
    private BairroRepository bairroRepository;

    public void cadastrarBairro(Bairro bairro) {
        Optional<Bairro> bairroOpt =
                Optional.ofNullable(bairroRepository.buscarBairroPorNome(bairro.getNomeBairro()));
        if (bairroOpt.isEmpty()){
            bairroRepository.cadastrarBairro(bairro);
        }else{
            throw new EntityIntegrityViolationException("Bairro já cadastrado");
        }
    }

    public Bairro buscarBairroPorId(int idBairro) {
        return  bairroRepository.buscarBairroPorId(idBairro);
    }

    public List<Bairro> listarBairros() {
        return bairroRepository.listarBairros();
    }

    public void atualizarBairro(Bairro bairro) {
        bairroRepository.atualizarBairro(bairro);
    }

    public void deletarBairro(int idBairro) {
        bairroRepository.deletarBairro(idBairro);
    }

}

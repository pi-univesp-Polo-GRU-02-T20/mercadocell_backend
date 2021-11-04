package br.com.univesp.mercadocell.mercadocell.service;

import br.com.univesp.mercadocell.mercadocell.model.Bairro;
import br.com.univesp.mercadocell.mercadocell.repository.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BairroService {

    @Autowired
    private static BairroRepository bairroRepository;

    public void cadastrarBairro(Bairro bairro) {
        bairroRepository.cadastrarBairro(bairro);
    }

    public static Bairro buscarBairroPorId(int idBairro) {
        return (Bairro) bairroRepository.buscarBairroPorId(idBairro);
    }

    public static List<Bairro> listarBairros() {
        return bairroRepository.listarBairros();
    }

    public static void atualizarBairro(Bairro bairro) {
        bairroRepository.atualizarBairro(bairro);
    }

    public static void deletarBairro(int idBairro) {
        bairroRepository.deletarBairro(idBairro);
    }

}

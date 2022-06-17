package br.com.univesp.mercadocell.mercadocell.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private Integer codProduto;
    //@NotEmpty(message = "O nome do Produto deve ser preenchido")
    private String nomeProduto;
    private String descricaoProduto;
    private SubCategoria subCategoria;
    private UnidadeMedida unidadeMedida;
    private Integer quantidadeEstoqueMinima;
    private Integer quantidadeEstoqueMaxima;
    private Integer quantidadeEstoqueAtual;
}

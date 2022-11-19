package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.dto.ImagemDTO;
import br.com.univesp.mercadocell.mercadocell.dto.ImagemProdutoDTO;
import br.com.univesp.mercadocell.mercadocell.message.ResponseFile;
import br.com.univesp.mercadocell.mercadocell.model.Imagem;
import br.com.univesp.mercadocell.mercadocell.service.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("imagem")
public class ImagemController {
    @Autowired
    private ImagemService imagemService;
    public static final String URL_IMAGENS_PRODUTO = "imagem/";


    @GetMapping(path="/{imagemId}")
    public ResponseEntity<InputStreamResource> buscarImagemPorId(@PathVariable Integer imagemId) {
        Imagem img = imagemService.buscarImagemPorId(imagemId.intValue());

        MediaType mediaTypeFoto = MediaType.parseMediaType(img.getTipoImagem());
        return ResponseEntity.ok()
                .contentType(mediaTypeFoto)
                //.header(HttpHeaders.CONTENT_DISPOSITION,
                //        "attachment; filename=\"" + img.getNomeImagem() + "\"")
                .body(new InputStreamResource(new ByteArrayInputStream(img.getBinarioImagem())));
    }

    @PostMapping(path="/produto/{produtoId}")
    public ResponseEntity<ImagemDTO> cadastrarImagem(@PathVariable Integer produtoId,
                                                        @RequestParam("arqImagem") MultipartFile arqImagem ) {
        imagemService.cadastrarImagemProduto(produtoId.intValue(), imagemService.converteMultipartFileParaImagem(arqImagem));
        return ResponseEntity.accepted().
                body(   new ImagemDTO(
                            null,
                            StringUtils.cleanPath(arqImagem.getOriginalFilename()),
                            arqImagem.getContentType()
                        )
                );

    }

/* Método desativado para vinculo de imagem e produto por meio do método de cadastro de imagem
    @PostMapping("/vincularImagemProduto")
    public ResponseEntity<ImagemProdutoDTO> vincularImagemProduto( @RequestBody ImagemProdutoDTO imagemProdutoDTO) {
        imagemService.vincularImagemProduto(imagemProdutoDTO);
        return ResponseEntity.accepted().build();
    }
 */
    private void vincularImagemProduto( Integer imagemId, Integer produtoId) {
        imagemService.vincularImagemProduto( new ImagemProdutoDTO(imagemId, produtoId));
    }


    @PutMapping
    public ResponseEntity<ImagemDTO> atualizarImagem( @RequestBody MultipartFile arqImagem) {
        imagemService.atualizarImagem(imagemService.converteMultipartFileParaImagem(arqImagem));
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{imagemId}")
    public ResponseEntity<ImagemDTO> deletarImagem(@PathVariable int imagemId) {
        imagemService.deletarImagem(imagemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path="/produto/{produtoId}")
    public ResponseEntity<List<ResponseFile>> buscarImagemProdutoPorId(@PathVariable Integer produtoId) {
        List<ResponseFile> arquivosImagem = imagemService.buscarImagemProdutoPorId(produtoId.intValue()).stream()
                .map(imagem -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path(URL_IMAGENS_PRODUTO)
                            .path(imagem.getCodigoImagem().toString())
                            .toUriString();
                    return new ResponseFile(
                            imagem.getNomeImagem(),
                            fileDownloadUri,
                            imagem.getTipoImagem(),
                            imagem.getBinarioImagem().length);
                }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(arquivosImagem);
    }

}

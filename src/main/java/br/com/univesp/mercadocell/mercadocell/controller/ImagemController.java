package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.dto.ImagemDTO;
import br.com.univesp.mercadocell.mercadocell.message.ResponseFile;
import br.com.univesp.mercadocell.mercadocell.model.Imagem;
import br.com.univesp.mercadocell.mercadocell.service.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("imagem")
public class ImagemController {
    @Autowired
    private ImagemService imagemService;
    private static final String URL_IMAGENS_PRODUTO = "imagem/";

    @GetMapping(path="/{idImagem}")
    public ResponseEntity<byte[]> buscarImagemPorId(@PathVariable Integer idImagem) {
        Imagem img = imagemService.buscarImagemPorId(idImagem.intValue());
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + img.getNomeImagem() + "\"")
                .body(img.getBinarioImagem());
    }



    @PostMapping
    public ResponseEntity<ImagemDTO> cadastrarImagem(@RequestBody MultipartFile arqImagem ) {
        imagemService.cadastrarImagem(arqImagem);
        return ResponseEntity.accepted().
                body(   new ImagemDTO(
                        StringUtils.cleanPath(arqImagem.getOriginalFilename()),
                        arqImagem.getContentType())
                );
    }

    @PutMapping
    public ResponseEntity<ImagemDTO> atualizarImagem( @RequestBody MultipartFile arqImagem) {
        imagemService.atualizarImagem(arqImagem);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idImagem}")
    public ResponseEntity<ImagemDTO> deletarImagem(@PathVariable int idImagem) {
        imagemService.deletarImagem(idImagem);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path="/produto/{idProduto}")
    public ResponseEntity<List<ResponseFile>> buscarImagemProdutoPorId(@PathVariable Integer idProduto) {
        List<ResponseFile> arquivosImagem = imagemService.buscarImagemProdutoPorId(idProduto.intValue()).stream()
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

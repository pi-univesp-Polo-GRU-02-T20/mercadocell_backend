package br.com.univesp.mercadocell.mercadocell.controller;

import br.com.univesp.mercadocell.mercadocell.dto.ImagemDTO;
import br.com.univesp.mercadocell.mercadocell.model.Imagem;
import br.com.univesp.mercadocell.mercadocell.service.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("imagem")
public class ImagemController {
    @Autowired
    private ImagemService imagemService;

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



/*


    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Categoria> deletarCategoria(@PathVariable int idCategoria) {
        imagemService.deletarCategoria(idCategoria);
        return ResponseEntity.noContent().build();
    }


 */
}

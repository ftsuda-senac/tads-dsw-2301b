package br.senac.tads.dsw.dadospessoais;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping
    public ResponseEntity<Void> receberArquivo(@RequestParam MultipartFile arquivo) {
        if (arquivo == null || arquivo.isEmpty()) {
            // ERRO
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Arquivo inválido");
        }
        try {
            byte[] bytesArquivo = arquivo.getBytes();

            Path destino = Paths.get("C:/senac/servidor-upload/"
                    + arquivo.getOriginalFilename());
            Files.write(destino, bytesArquivo);

            // Criar URL do arquivo
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .replacePath("/arquivos-upload/{nomeArquivo}")
                    .buildAndExpand(arquivo.getOriginalFilename())
                    .toUri();
            return ResponseEntity.created(location).build();

        } catch (IOException ex) {
            // ERRO
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro durante upload - " + ex.getMessage());
        }
    }

    @GetMapping("/listar")
    public List<String> listarArquivosImagens() {
        File diretorio = new File("C:/senac/servidor-upload/");
        File[] listaArquivos = diretorio.listFiles();

        List<String> arquivosImagem = new ArrayList<>();
        for (File arquivo : listaArquivos) {
            if (arquivo.getName().endsWith("jpg") || arquivo.getName().endsWith("png")) {
                URI imageLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                        .replacePath("/arquivos-upload/{nomeArquivo}")
                        .buildAndExpand(arquivo.getName())
                        .toUri();
                arquivosImagem.add(imageLocation.toString());
            }
        }
        return arquivosImagem;
    }
}

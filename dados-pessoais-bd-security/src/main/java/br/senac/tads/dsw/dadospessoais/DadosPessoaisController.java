package br.senac.tads.dsw.dadospessoais;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/dados-pessoais")
public class DadosPessoaisController {

    @Autowired
    private DadosPessoaisService service;

    @GetMapping
    public List<DadosPessoaisDto> findAll(
        @RequestParam(value = "pagina", defaultValue = "0") int pagina,
        @RequestParam(value = "quantidade", defaultValue = "3") int quantidade,
        @RequestParam(value = "textoBusca", required = false) String textoBusca) {
            return service.findAll(pagina, quantidade, textoBusca);
    }
    @GetMapping("/paged")
    public Page<DadosPessoaisDto> findAllPaged(
        @RequestParam(value = "pagina", defaultValue = "0") int pagina,
        @RequestParam(value = "quantidade", defaultValue = "3") int quantidade,
        @RequestParam(value = "textoBusca", required = false) String textoBusca) {
            return service.findAllPaged(pagina, quantidade, textoBusca);
    }

    @GetMapping("/{apelido}")
    public DadosPessoaisDto findByApelido(@PathVariable String apelido) {
        return service.findByApelido(apelido);
    }

    @PostMapping
    public ResponseEntity<Void> addNew(@RequestBody @Valid DadosPessoaisDto dados) {
        service.addNew(dados);
        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{apelido}")
        .buildAndExpand(dados.apelido()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{apelido}")
    public void update(@PathVariable String apelido, 
            @RequestBody @Valid DadosPessoaisDto dados) {
        service.update(apelido, dados);
    }

    @DeleteMapping("/{apelido}")
    public void delete(@PathVariable String apelido) {
        service.delete(apelido);
    }
    
}

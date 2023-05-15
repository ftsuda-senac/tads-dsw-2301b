package br.senac.tads.dsw.dadospessoais;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/conhecimentos")
public class ConhecimentoController {

    @Autowired
    private ConhecimentoRepository repository;

    @GetMapping
    public List<Conhecimento> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Conhecimento findById(@PathVariable Integer id) {
        Optional<Conhecimento> optResultado = repository.findById(id);
        if (optResultado.isPresent()) {
            return optResultado.get();
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Conhecimento com ID " + id + " não encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<Void> addNew(@RequestBody Conhecimento c) {
        repository.save(c);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(c.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Conhecimento c) {
        Optional<Conhecimento> optResultado = repository.findById(id);
        if (!optResultado.isPresent()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Conhecimento com ID " + id + " não encontrado");
        }
        c.setId(id);
        repository.save(c);
        return ResponseEntity.ok().build();
    }
    
}

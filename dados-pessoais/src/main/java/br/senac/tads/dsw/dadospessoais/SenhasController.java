package br.senac.tads.dsw.dadospessoais;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/senhas")
public class SenhasController {
    
    @PostMapping
    public Senhas receberSenhas(@RequestBody @Valid Senhas senhas) {
        return senhas;
    }

}

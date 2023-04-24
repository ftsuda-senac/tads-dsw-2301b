package br.senac.tads.dsw.dadospessoais;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/senhas")
public class SenhasController {
    
    @PostMapping
    public Senhas receberSenhas(@RequestBody @Valid Senhas senhas) {
        String hash = BCrypt.hashpw(senhas.getValor(), BCrypt.gensalt());
        senhas.setHash(hash);
        return senhas;
    }

    @GetMapping(value = "/validar", produces = "text/plain")
    public String validarSenha(
        @RequestParam("senha") String senhaOriginal,
        @RequestParam("hash") String hash) {
            boolean resultado = BCrypt.checkpw(senhaOriginal, hash);
            if (resultado == true) {
                return "SUCESSO";
            }
            return "ERRO";
    }

}

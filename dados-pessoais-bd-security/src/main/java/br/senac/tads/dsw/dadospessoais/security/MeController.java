package br.senac.tads.dsw.dadospessoais.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {

    @Autowired
    UsuarioService service;

    @GetMapping("/me")
    public UsuarioDto findUsuario(Authentication auth) {

        String username = (String) auth.getPrincipal();

        Usuario usuarioCadastro = service.loadUserByUsername(username);
        UsuarioDto dto = new UsuarioDto();
        dto.setNome(usuarioCadastro.getNome());
        dto.setEmail(usuarioCadastro.getEmail());
        dto.setTelefone(usuarioCadastro.getTelefone());
        dto.setUrlFoto("http://localhost:8080/img/" + usuarioCadastro.getArquivoFoto());
        
        return dto;
    }

    
}

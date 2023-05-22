package br.senac.tads.dsw.dadospessoais.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEnconder;

    Map<String, Usuario> mapUsuarios = new HashMap<>();

    @PostConstruct
    public void init() {
        mapUsuarios.put("capitao",
            new Usuario("Steve Rogers", "capitao",
                passwordEnconder.encode("Abcd1234"),
                Arrays.asList(new Papel("ADMIN"), new Papel("USUARIO"))));
        mapUsuarios.put("soldado",
                new Usuario("Bucky Barnes", "soldado",
                passwordEnconder.encode("Abcd1234"),
                Arrays.asList(new Papel("USUARIO"))));
    }

    @Override
    public Usuario loadUserByUsername(String username)
        throws UsernameNotFoundException {
            Usuario usuario = mapUsuarios.get(username);
            if (usuario == null) {
                throw new UsernameNotFoundException(username);
            }
            return usuario;
    }
    
}

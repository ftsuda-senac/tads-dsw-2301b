package br.senac.tads.dsw.dadospessoais.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private AssinadorComponent assinador;


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String senha) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, senha));
        Usuario usuario = (Usuario) auth.getPrincipal();

        Claims claims = Jwts.claims()
        .setSubject(usuario.getUsername());
        claims.put("name", "Steve Rogers");
        

        Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiration = issuedAt.plus(1, ChronoUnit.HOURS);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date.from(issuedAt))
            .setExpiration(Date.from(expiration))
            .signWith(assinador.getChaveAssinatura())
            .compact();
    
    }

    
}
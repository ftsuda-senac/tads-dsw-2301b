package br.senac.tads.dsw.dadospessoais.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

@Component
public class AssinadorComponent {

    public SecretKey getChaveAssinatura() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            String segredo = "53greD0$$$";
            SecretKey chaveAssinatura = Keys.hmacShaKeyFor(md.digest(segredo.getBytes(StandardCharsets.UTF_8)));
            return chaveAssinatura;
        } catch(Exception ex) {
            // A PRINCIPIO, NAO DEVE OCORRER ERRO
            throw new RuntimeException(ex);
        }
    }
    
}

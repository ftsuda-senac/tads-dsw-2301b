package br.senac.tads.dsw.dadospessoais.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private AssinadorComponent assinador;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String cabecalhoAuth = request.getHeader("Authorization");
        // "Bearer [TOKEN]"
        if (cabecalhoAuth != null && cabecalhoAuth.startsWith("Bearer")) {
            String token = cabecalhoAuth.substring(7);

            // Abrir e extrair informações do token
            Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(assinador.getChaveAssinatura())
                .build()
                .parseClaimsJws(token);
    
            Authentication auth = 
            new UsernamePasswordAuthenticationToken(
                claims.getBody().getSubject(),
                "",
                new ArrayList<Papel>());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            SecurityContextHolder.clearContext();
        }


        filterChain.doFilter(request, response);

    }
    
}

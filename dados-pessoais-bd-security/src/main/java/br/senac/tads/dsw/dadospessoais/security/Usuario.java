package br.senac.tads.dsw.dadospessoais.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

public class Usuario implements UserDetails {

    private String username;

    private String hashSenha;

    private List<Papel> papeis;

    public Usuario() {
        
    }

    public Usuario(String username, String hashSenha, List<Papel> papeis) {
        this.username = username;
        this.hashSenha = hashSenha;
        this.papeis = papeis;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public void setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
    }

    public List<Papel> getPapeis() {
        return papeis;
    }

    public void setPapeis(List<Papel> papeis) {
        this.papeis = papeis;
    }

    @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    public List<Papel> getAuthorities() {
        return papeis;
    }

    @Override
    public String getPassword() {
        return hashSenha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}

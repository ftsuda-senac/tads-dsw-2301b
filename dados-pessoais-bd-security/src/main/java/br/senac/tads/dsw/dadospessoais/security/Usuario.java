package br.senac.tads.dsw.dadospessoais.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

public class Usuario implements UserDetails {

    private String nome;

    private String username;

    private String hashSenha;

    private String email;

    private String telefone;

    private String arquivoFoto;

    private List<Papel> papeis;

    public Usuario() {
        
    }

    public Usuario(String nome, String username, String hashSenha, List<Papel> papeis) {
        this.nome = nome;
        this.username = username;
        this.hashSenha = hashSenha;
        this.papeis = papeis;
    }

    public Usuario(String nome, String username, String hashSenha, String telefone, String email, String arquivoFoto, List<Papel> papeis) {
        this(nome, username, hashSenha, papeis);
        this.email = email;
        this.telefone = telefone;
        this.arquivoFoto = arquivoFoto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getArquivoFoto() {
        return arquivoFoto;
    }

    public void setArquivoFoto(String arquivoFoto) {
        this.arquivoFoto = arquivoFoto;
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

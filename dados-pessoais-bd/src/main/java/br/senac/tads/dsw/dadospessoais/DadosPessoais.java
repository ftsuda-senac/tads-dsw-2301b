package br.senac.tads.dsw.dadospessoais;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class DadosPessoais {

    private Integer id;

    @NotBlank(message = "Preencha seu nome completo seu animal")
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(max = 64)
    private String apelido;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    @Size(max = 15)
    private String telefone;

    @PastOrPresent
    private LocalDate dataNascimento;

    @Size(min = 1, message = "Selecione pelo menos 1 conhecimento")
    private List<Conhecimento> conhecimentos;

    public DadosPessoais() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Conhecimento> getConhecimentos() {
        return conhecimentos;
    }

    public void setConhecimentos(List<Conhecimento> conhecimentos) {
        this.conhecimentos = conhecimentos;
    }

}

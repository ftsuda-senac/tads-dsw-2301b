package br.senac.tads.dsw.dadospessoais;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pessoa")
public class DadosPessoais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Preencha seu nome completo seu animal")
    @Size(max = 100)
    @Column(name = "nome_completo")
    private String nome;

    @NotBlank
    @Size(max = 64)
    @Column(unique = true)
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
    @ManyToMany
    @JoinTable(
        name = "pessoa_conhecimento",
        joinColumns = @JoinColumn(name = "pessoa_id"),
        inverseJoinColumns = @JoinColumn(name = "conhecimento_id"))
    private Set<Conhecimento> conhecimentos;

    // mappedBy = Nome do atributo "pessoa" na classe FotoPessoa onde foi configurado o @ManyToOne
    @OneToMany(mappedBy = "pessoa")
    private Set<FotoPessoa> fotos;

    @Column
    private String senha;

    @Transient // Indica que o campo não tem coluna na tabela no banco de dados
    private String senhaRepetida;

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

    public Set<Conhecimento> getConhecimentos() {
        return conhecimentos;
    }

    public void setConhecimentos(Set<Conhecimento> conhecimentos) {
        this.conhecimentos = conhecimentos;
    }

    public Set<FotoPessoa> getFotos() {
        return fotos;
    }

    public void setFotos(Set<FotoPessoa> fotos) {
        this.fotos = fotos;
    }

}

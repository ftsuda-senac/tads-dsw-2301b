package br.senac.tads.dsw.dadospessoais;

public class Conhecimento {

    private Integer id;

    private String nome;

    public Conhecimento() {
    }

    public Conhecimento(String nome) {
        this.nome = nome;
    }

    public Conhecimento(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

}

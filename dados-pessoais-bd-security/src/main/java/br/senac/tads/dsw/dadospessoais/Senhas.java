package br.senac.tads.dsw.dadospessoais;

import br.senac.tads.dsw.dadospessoais.validacao.SenhasIguais;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@SenhasIguais
public class Senhas {

    @NotBlank
    @Size(min = 8)
    @Pattern.List({
        @Pattern(regexp = "[a-z]{1,}", message = "Não tem caractere minúsculo"),
        @Pattern(regexp = "[A-Z]{1,}", message = "Não tem caractere maiúsculo"),
        @Pattern(regexp = "[0-9]{1,}", message = "Não tem caractere numérico"),
        @Pattern(regexp = "[!@#$%^&*+=?-_()/\"\\.,<>~`;:]{1,}", message = "Não tem caractere especial")
    })
    // @Pattern(regexp = "[a-z]{1,}", message = "Não tem caractere minúsculo")
    // @Pattern(regexp = "[A-Z]{1,}", message = "Não tem caractere maiúsculo")
    // @Pattern(regexp = "[0-9]{1,}", message = "Não tem caractere numérico")
    // @Pattern(regexp = "[!@#$%^&*()\\-__+.]{1,}", message = "Não tem caractere especial")
    // @Pattern(regexp = "(?=(.*[a-z]){1,})(?=(.*[0-9]){1,})(?=(.*[!@#$%^&*()\\-__+.]){1,}).{8,}$")
    private String valor;

    private String repeticao;

    private String hash;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(String repeticao) {
        this.repeticao = repeticao;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
}

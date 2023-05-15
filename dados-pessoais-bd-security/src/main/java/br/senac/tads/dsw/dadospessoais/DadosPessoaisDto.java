package br.senac.tads.dsw.dadospessoais;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record DadosPessoaisDto(
    @NotBlank
    @Size(max = 64)
    String apelido,

    @NotBlank(message = "Preencha seu nome completo seu animal")
    @Size(max = 100)
    String nome,
    
    @NotBlank
    @Size(max = 100)
    @Email
    String email,
    String telefone,
    
    @PastOrPresent
    LocalDate dataNascimento,

    @Size(min = 1, message = "Selecione pelo menos 1 conhecimento")
    List<Integer> conhecimentosIds,
    List<FotoPessoaDto> fotos
) {
    
}

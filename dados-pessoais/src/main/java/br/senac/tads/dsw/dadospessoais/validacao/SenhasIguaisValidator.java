package br.senac.tads.dsw.dadospessoais.validacao;

import br.senac.tads.dsw.dadospessoais.Senhas;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhasIguaisValidator implements 
    ConstraintValidator<SenhasIguais, Senhas> {

    @Override
    public boolean isValid(Senhas senhas, ConstraintValidatorContext context) {
        boolean resultado = senhas.getValor().equals(senhas.getRepeticao());
        return resultado;
    }
    
}

package br.senac.tads.dsw.dadospessoais;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConhecimentoRepository
    extends JpaRepository<Conhecimento, Integer> {
    
}

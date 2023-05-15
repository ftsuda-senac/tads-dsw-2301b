package br.senac.tads.dsw.dadospessoais;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosPessoaisRepository 
    extends JpaRepository<DadosPessoais, Integer> {

    Optional<DadosPessoais> findByApelido(String apelido);

    List<DadosPessoais> findByNomeContainingIgnoreCase(String textoBusca);

    Optional<DadosPessoais> findByApelidoIgnoreCase(String apelido);

    boolean existsByApelido(String apelido);

    void deleteByApelido(String apelido);
    
}

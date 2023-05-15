package br.senac.tads.dsw.dadospessoais;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

// Comentando integração com Interface para evitar conflitos
// com Spring Data JPA
public class ConhecimentoRepositoryJpa  /* implements ConhecimentoRepository */ {

    @PersistenceContext
    private EntityManager em;

    // @Override
    public List<Conhecimento> findAll() {
        TypedQuery<Conhecimento> jpqlQuery = 
            em.createQuery("SELECT c FROM Conhecimento c", Conhecimento.class);
        List<Conhecimento> resultados = jpqlQuery.getResultList();
        return resultados;
    }

    // @Override
    public Optional<Conhecimento> findById(Integer id) {
        TypedQuery<Conhecimento> jpqlQuery =
            em.createQuery("SELECT c FROM Conhecimento c WHERE c.id = :idParam", 
                Conhecimento.class);
        jpqlQuery.setParameter("idParam", id);

        try {
            Conhecimento resultado = jpqlQuery.getSingleResult();
            return Optional.of(resultado);
        } catch (NoResultException ex) {
            return Optional.empty();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    // @Override
    @Transactional
    public Conhecimento save(Conhecimento c) {
        if (c.getId() == null) {
            // Se ID for nulo, salva novo conhecimento
            em.persist(c);
        } else {
            // Se ID for informado, atualiza conhecimento existente
            c = em.merge(c);
        }
        return c;
    }
    
}

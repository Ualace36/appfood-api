package com.ualace.appfood.infrastructure.repository;

import com.ualace.appfood.domain.model.Cozinha;
import com.ualace.appfood.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {
        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }

    @Override
    public Cozinha buscarPorId(Long id) {
        return manager.find(Cozinha.class, id);
    }
     @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }
    @Transactional
    @Override
    public void remover(Cozinha cozinha) {
        cozinha = buscarPorId(cozinha.getId());
        manager.remove(cozinha);
    }
}

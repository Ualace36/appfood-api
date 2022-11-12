package com.ualace.appfood.infrastructure.repository;

import com.ualace.appfood.domain.model.Estado;
import com.ualace.appfood.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        return manager.createQuery("from Estado", Estado.class)
                .getResultList();
    }

    @Override
    public Estado buscarPorId(Long id) {
        return manager.find(Estado.class, id);

    }

    @Transactional
    @Override
    public Estado salvar(Estado estado) {
        return manager.merge(estado);
    }

    @Transactional
    @Override
    public void remover(Long id) {
    Estado estado = buscarPorId(id);
    if (estado == null){
        throw new EmptyResultDataAccessException(1);
    }
       manager.remove(estado);
    }
}

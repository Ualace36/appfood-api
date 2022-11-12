package com.ualace.appfood.infrastructure.repository;

import com.ualace.appfood.domain.model.Cidade;
import com.ualace.appfood.domain.model.Estado;
import com.ualace.appfood.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class CidadeRepositoryImpl implements CidadeRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> listar() {
        return manager.createQuery("from Cidade", Cidade.class)
                .getResultList();
    }

    @Override
    public Cidade buscarPorId(Long id) {
        return manager.find(Cidade.class,id);
    }

    @Transactional
    @Override
    public Cidade salvar(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Cidade cidade = buscarPorId(id);
        if (cidade == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cidade);
    }
}
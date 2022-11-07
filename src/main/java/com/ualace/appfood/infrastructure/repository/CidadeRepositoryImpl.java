package com.ualace.appfood.infrastructure.repository;

import com.ualace.appfood.domain.model.Cidade;
import com.ualace.appfood.domain.repository.CidadeRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public void remover(Cidade cidade) {
     cidade = buscarPorId(cidade.getIdCidade());
     manager.remove(cidade);
    }
}

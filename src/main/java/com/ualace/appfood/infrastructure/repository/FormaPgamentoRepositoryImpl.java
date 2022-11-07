package com.ualace.appfood.infrastructure.repository;

import com.ualace.appfood.domain.model.Cozinha;
import com.ualace.appfood.domain.model.FormaDePagamento;
import com.ualace.appfood.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class FormaPgamentoRepositoryImpl implements FormaPagamentoRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormaDePagamento> listar() {
        return manager.createQuery("from Cozinha", FormaDePagamento.class)
                .getResultList();
    }

    @Override
    public FormaDePagamento buscarPorId(Long id) {
        return manager.find(FormaDePagamento.class, id);
    }

    @Override
    public FormaDePagamento salvar(Cozinha cozinha) {
        return null;
    }

    @Transactional
    @Override
    public FormaDePagamento salvar(FormaDePagamento FormaDePagamento) {
        return manager.merge(FormaDePagamento);
    }
    @Transactional
    @Override
    public void remover(FormaDePagamento formaDePagamento) {
        formaDePagamento = buscarPorId(formaDePagamento.getId());
        manager.remove(formaDePagamento);
    }
}

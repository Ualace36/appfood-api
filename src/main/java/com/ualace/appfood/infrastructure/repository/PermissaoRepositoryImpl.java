package com.ualace.appfood.infrastructure.repository;

import com.ualace.appfood.domain.model.Cozinha;
import com.ualace.appfood.domain.model.Permissao;
import com.ualace.appfood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Permissao> listar() {
        return manager.createQuery("from Permissao", Permissao.class)
                .getResultList();
    }

    @Override
    public Permissao buscarPorId(Long id) {
        return manager.find(Permissao.class,id);
    }

    @Override
    public Permissao salvar(Permissao permissao) {
        return manager.merge(permissao);
    }
    @Transactional
    @Override
    public void remover(Permissao permissao) {
      permissao = buscarPorId(permissao.getIdPermissao());
      manager.remove(permissao);
    }


}

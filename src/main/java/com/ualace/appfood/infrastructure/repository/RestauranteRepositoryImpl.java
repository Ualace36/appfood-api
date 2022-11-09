package com.ualace.appfood.infrastructure.repository;

import com.ualace.appfood.domain.model.Restaurante;
import com.ualace.appfood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> listar() {
        return manager.createQuery("from Restaurante", Restaurante.class)
                .getResultList();
    }

    @Override
    public Restaurante buscarPorId(Long id) {
        return manager.find(Restaurante.class, id);
    }
     @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return manager.merge(restaurante);
    }

    @Transactional
    @Override
    public void remover(Restaurante restaurante) {
      restaurante = buscarPorId(restaurante.getId());
              manager.remove(restaurante);
    }
}

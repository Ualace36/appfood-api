package com.ualace.appfood.infrastructure.repository;

import com.ualace.appfood.domain.model.Restaurante;
import com.ualace.appfood.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public Restaurante buscarPorId(Long idRestaurante) {
        return manager.find(Restaurante.class, idRestaurante);
    }
     @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return manager.merge(restaurante);
    }

    @Transactional
    @Override
    public void remover(Long idRestaurante) {
      Restaurante restaurante = buscarPorId(idRestaurante);
      if(restaurante == null){
          throw new EmptyResultDataAccessException(1);
      }
              manager.remove(restaurante);
    }
}

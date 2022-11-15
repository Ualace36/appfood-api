package com.ualace.appfood.domain.infrastructure;

import com.ualace.appfood.domain.model.Restaurante;
import com.ualace.appfood.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){

        var jpql = "from Restaurante where nome like :nome " + " and taxaFrete between :taxaFinal";
     return manager.createNativeQuery(jpql, Restaurante.class)
             .setParameter("nome" , "%" + nome + "%" )
             .setParameter("taxaFreteInicial", taxaFreteFinal)
             .setParameter("taxaFinal", taxaFreteFinal)
             .getResultList();
    }
}

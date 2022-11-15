package com.ualace.appfood.domain.repository;

import com.ualace.appfood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries {
    //Esses métodos eram utilizados antes de eu utilizar DATA JPA
    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
//    Restaurante buscarPorId(Long idRestaurante);
//    Restaurante salvar(Restaurante restaurante);
//    void remover(Long idRestaurante);
}

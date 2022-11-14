package com.ualace.appfood.domain.repository;

import com.ualace.appfood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    //Esses métodos eram utilizados antes de eu utilizar o DATA JPA
//    List<Restaurante> listar();
//    Restaurante buscarPorId(Long idRestaurante);
//    Restaurante salvar(Restaurante restaurante);
//    void remover(Long idRestaurante);
}

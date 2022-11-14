package com.ualace.appfood.domain.repository;

import com.ualace.appfood.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
//  Esses métodos eram utilizados antes de extendermos a JpaRepository
//    List<Estado> listar();
//    Estado buscarPorId(Long id);
//    Estado salvar(Estado estado);
//    void remover(Long id);
}

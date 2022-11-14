package com.ualace.appfood.domain.repository;

import com.ualace.appfood.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
//  Esses métodos eram usados antes de extender JpaRepository
//    List<Cidade> listar();
//    Cidade buscarPorId(Long id);
//    Cidade salvar(Cidade cidade);
//    void remover(Long idCidade);
}

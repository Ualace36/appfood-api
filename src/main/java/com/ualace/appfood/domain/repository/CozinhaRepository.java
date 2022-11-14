package com.ualace.appfood.domain.repository;

import com.ualace.appfood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
    //TODO a partir de agora iremos utilizar os métodos do DATA JPA
//    List<Cozinha> listar();
//    Cozinha buscarPorId(Long id);
//    Cozinha salvar(Cozinha cozinha);
//    void remover(Long id);
}

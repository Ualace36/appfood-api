package com.ualace.appfood.domain.repository;

import com.ualace.appfood.domain.model.Cozinha;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> listar();
    Cozinha buscarPorId(Long id);
    Cozinha salvar(Cozinha cozinha);
    void remover(Long id);
}

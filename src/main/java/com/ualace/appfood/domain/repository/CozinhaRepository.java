package com.ualace.appfood.domain.repository;

import com.ualace.appfood.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> listar();
    Cozinha buscarPorId(Long id);
    Cozinha salvar(Cozinha cozinha);
    void remover(Cozinha cozinha);
}

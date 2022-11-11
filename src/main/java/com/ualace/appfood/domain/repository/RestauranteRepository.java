package com.ualace.appfood.domain.repository;

import com.ualace.appfood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> listar();
    Restaurante buscarPorId(Long idRestaurante);
    Restaurante salvar(Restaurante restaurante);
    void remover(Long idRestaurante);
}

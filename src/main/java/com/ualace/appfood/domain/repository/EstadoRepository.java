package com.ualace.appfood.domain.repository;

import com.ualace.appfood.domain.model.Cozinha;
import com.ualace.appfood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();
    Estado buscarPorId(Long id);
    Estado salvar(Estado estado);
    void remover(Estado estado);
}

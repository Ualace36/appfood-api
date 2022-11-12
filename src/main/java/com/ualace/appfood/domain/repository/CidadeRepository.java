package com.ualace.appfood.domain.repository;

import com.ualace.appfood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> listar();
    Cidade buscarPorId(Long id);
    Cidade salvar(Cidade cidade);
    void remover(Long idCidade);
}

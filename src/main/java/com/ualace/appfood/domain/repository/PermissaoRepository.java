package com.ualace.appfood.domain.repository;


import com.ualace.appfood.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {
    List<Permissao> listar();
    Permissao buscarPorId(Long id);
    Permissao salvar(Permissao permissao);
    void remover(Permissao permissao);


}

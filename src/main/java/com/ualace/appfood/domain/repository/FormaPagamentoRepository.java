package com.ualace.appfood.domain.repository;

import com.ualace.appfood.domain.model.Cozinha;
import com.ualace.appfood.domain.model.FormaDePagamento;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FormaPagamentoRepository {
    List<FormaDePagamento> listar();
    FormaDePagamento buscarPorId(Long id);
    FormaDePagamento salvar(Cozinha cozinha);

    @Transactional
    FormaDePagamento salvar(FormaDePagamento FormaDePagamento);

    void remover(FormaDePagamento formaDePagamento);
}

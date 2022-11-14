package com.ualace.appfood.domain.repository;

import com.ualace.appfood.domain.model.FormaDePagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FormaPagamentoRepository extends JpaRepository <FormaDePagamento, Long> {

//    List<FormaDePagamento> listar();
//    FormaDePagamento buscarPorId(Long id);
//    FormaDePagamento salvar(Cozinha cozinha);
//
//    @Transactional
//    FormaDePagamento salvar(FormaDePagamento FormaDePagamento);
//
//    void remover(FormaDePagamento formaDePagamento);
}

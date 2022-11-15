package com.ualace.appfood.domain.repository;

import com.ualace.appfood.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {
    List<Restaurante> find(String nome,
                           BigDecimal taxaFreteInicial,
                           BigDecimal taxaFreteFinal);
}

package com.ualace.appfood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ualace.appfood.domain.model.Cozinha;
import com.ualace.appfood.domain.model.Endereco;
import com.ualace.appfood.domain.model.FormaDePagamento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {
    //Com essa anotation ignora o nome no momento da serelização, mas o considera na descerelização
    @JsonIgnoreProperties(value =  "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    private List<FormaDePagamento> formasPagamento = new ArrayList<>();
}

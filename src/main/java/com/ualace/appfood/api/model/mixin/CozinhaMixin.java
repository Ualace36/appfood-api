package com.ualace.appfood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ualace.appfood.domain.model.Restaurante;

import java.util.List;

public class CozinhaMixin {
    @JsonIgnore
    private List<Restaurante> restaurantes;
}

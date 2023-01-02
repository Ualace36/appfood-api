package com.ualace.appfood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ualace.appfood.domain.model.Estado;

public class CidadeMixin {
    @JsonIgnoreProperties(value =  "nome", allowGetters = true)
    private Estado estado;
}

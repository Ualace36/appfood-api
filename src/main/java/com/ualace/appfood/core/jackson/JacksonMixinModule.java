package com.ualace.appfood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ualace.appfood.api.model.mixin.RestauranteMixin;
import com.ualace.appfood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {
  private static final long serialVersionUID = 1L;
  public JacksonMixinModule (){
      setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
  }
}

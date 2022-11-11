package com.ualace.appfood.api.controller;


import com.ualace.appfood.domain.model.Restaurante;
import com.ualace.appfood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping( produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<Restaurante> listar(){
        return restauranteRepository.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> obterPorId(Long id){
        Restaurante restaurante =  restauranteRepository.buscarPorId(id);
        if (restaurante != null){
            return ResponseEntity.ok(restaurante);
    }else { return ResponseEntity.notFound().build();
        }
    }
}

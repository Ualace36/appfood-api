package com.ualace.appfood.api.controller;


import com.ualace.appfood.domain.exception.EntidadeNaoEncontradaException;
import com.ualace.appfood.domain.model.Restaurante;
import com.ualace.appfood.domain.repository.RestauranteRepository;
import com.ualace.appfood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping( produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<Restaurante> listar(){
        return restauranteRepository.listar();
    }

    @GetMapping("/{idRestaurante}")
    public ResponseEntity<Restaurante> obterPorId(@PathVariable Long idRestaurante){
        Restaurante restaurante =  restauranteRepository.buscarPorId (idRestaurante);
        if (restaurante != null){
            return ResponseEntity.ok(restaurante);
    }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<?> adiicionar(@RequestBody Restaurante restaurante){
        try {
            Restaurante restaurante1 =  cadastroRestauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurante1);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}

package com.ualace.appfood.api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ualace.appfood.domain.exception.EntidadeEmUsoException;
import com.ualace.appfood.domain.exception.EntidadeNaoEncontradaException;
import com.ualace.appfood.domain.model.Restaurante;
import com.ualace.appfood.domain.repository.RestauranteRepository;
import com.ualace.appfood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }

    @GetMapping("/{idRestaurante}")
    public ResponseEntity<Restaurante> obterPorId(@PathVariable Long idRestaurante){
        Optional<Restaurante> restaurante =  restauranteRepository.findById (idRestaurante);
        if (restaurante.isPresent()){
            return ResponseEntity.ok(restaurante.get());
    }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
        try {
             restaurante =  cadastroRestauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurante);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{idRestaurante}")
    public ResponseEntity<?> atualizar(@PathVariable Long idRestaurante,
                                       @RequestBody Restaurante restaurante) {
        try {
           Restaurante restauranteAtual = restauranteRepository
                   .findById(idRestaurante).orElse(null);

            if (restauranteAtual != null) {
                BeanUtils.copyProperties(restaurante, restauranteAtual, "idRestaurante", "formasDePagamento", "endereco", "dataCadastro");

                restauranteAtual = cadastroRestauranteService.salvar(restauranteAtual);
                return ResponseEntity.ok(restauranteAtual);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{idRestaurante}")
    public ResponseEntity<?> excluir(@PathVariable Long idRestaurante){
        try {
            cadastroRestauranteService.excluir(idRestaurante);
            return ResponseEntity.noContent()
                    .build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound()
                    .build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .build();
        }
    }

    @PatchMapping("/{idRestaurante}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long idRestaurante,
                                              @RequestBody Map<String, Object> campos) {
        Restaurante restauranteAtual = restauranteRepository.findById(idRestaurante).orElse(null);

        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtual);

        return atualizar(idRestaurante, restauranteAtual);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

//			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

}

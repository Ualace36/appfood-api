package com.ualace.appfood.domain.service;

import com.ualace.appfood.domain.exception.EntidadeEmUsoException;
import com.ualace.appfood.domain.exception.EntidadeNaoEncontradaException;
import com.ualace.appfood.domain.model.Cozinha;
import com.ualace.appfood.domain.model.Restaurante;
import com.ualace.appfood.domain.repository.CozinhaRepository;
import com.ualace.appfood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante salvar(Restaurante restaurante){

        Long id = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscarPorId(id);
        if (cozinha == null){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com código %d", id));
        }
        restaurante.setCozinha(cozinha);
        return restauranteRepository.salvar(restaurante);
    }

    public void excluir(Long idRestaurante){
        try{
            restauranteRepository.remover(idRestaurante);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeEmUsoException(String.format("Não existe um cadastro de cozinha com o código %d", idRestaurante));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Restaurante de %d não pode ser removida, pois está em uso", idRestaurante));
        }
    }

}

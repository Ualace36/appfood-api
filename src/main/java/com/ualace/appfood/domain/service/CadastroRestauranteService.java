package com.ualace.appfood.domain.service;

import com.ualace.appfood.domain.exception.EntidadeEmUsoException;
import com.ualace.appfood.domain.model.Restaurante;
import com.ualace.appfood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante salvar(Restaurante restaurante){
        return restauranteRepository.salvar(restaurante);
    }

    public void excluir(Long id){
        try{
            restauranteRepository.remover(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeEmUsoException(String.format("Não existe um cadastro de cozinha com o código %d", id));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Restaurante de %d não pode ser removida, pois está em uso", id));
        }
    }

}

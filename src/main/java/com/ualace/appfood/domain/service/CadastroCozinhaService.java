package com.ualace.appfood.domain.service;

import com.ualace.appfood.domain.exception.EntidadeEmUsoException;
import com.ualace.appfood.domain.exception.EntidadeNaoEncontradaException;
import com.ualace.appfood.domain.model.Cozinha;
import com.ualace.appfood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long cozinhaId) {

        try {
            cozinhaRepository.deleteById(cozinhaId);


        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadstro de cozinha com código %d!", cozinhaId));


        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cozinha com o código %d não pode ser removida, pois está em uso!", cozinhaId));

        }
    }
}

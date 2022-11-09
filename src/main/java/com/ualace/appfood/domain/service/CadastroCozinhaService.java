package com.ualace.appfood.domain.service;

import com.ualace.appfood.domain.exception.EntidadeEmUsoException;
import com.ualace.appfood.domain.model.Cozinha;
import com.ualace.appfood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {
     @Autowired
     private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha){
         return  cozinhaRepository.salvar(cozinha);
    }
    public void excluir(Long id){
       try {
           cozinhaRepository.remover(id);
       }catch (
    DataIntegrityViolationException e) {
        throw new EntidadeEmUsoException(String.format("Cozinha de %d não pode ser removida, pois está em uso", id));

    }
    }
}

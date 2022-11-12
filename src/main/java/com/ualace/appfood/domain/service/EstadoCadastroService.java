package com.ualace.appfood.domain.service;

import com.ualace.appfood.domain.exception.EntidadeEmUsoException;
import com.ualace.appfood.domain.model.Estado;
import com.ualace.appfood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoCadastroService {
    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado){
        return estadoRepository.salvar(estado);
    }

    public void excluir(Long id){
        try {
            estadoRepository.remover(id);
        }catch (EmptyResultDataAccessException e) {
            throw new EntidadeEmUsoException(String.format("Não existe um cadstro de estado com código %d", id));


        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Estado de %d não pode ser removido, pois está em uso", id));

        }
    }
}
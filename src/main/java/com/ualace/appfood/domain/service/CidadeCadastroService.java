package com.ualace.appfood.domain.service;

import com.ualace.appfood.domain.exception.EntidadeEmUsoException;
import com.ualace.appfood.domain.exception.EntidadeNaoEncontradaException;
import com.ualace.appfood.domain.model.Cidade;
import com.ualace.appfood.domain.model.Estado;
import com.ualace.appfood.domain.repository.CidadeRepository;
import com.ualace.appfood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CidadeCadastroService {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade){
        Long id = cidade.getEstado().getIdEstado();
        Estado estado = estadoRepository.buscarPorId(id);
        if (estado == null){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de Estado com código %d", id));
        }cidade.setEstado(estado);
        return cidadeRepository.salvar(cidade);
    }

    public  void excluir(Long idCidade){
        try {
            cidadeRepository.remover(idCidade);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeEmUsoException(String.format("Não existe um cadastro de Estado com o código %d", idCidade));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Cidade de %d não pode ser removida, pois está em uso", idCidade));
        }
    }

}

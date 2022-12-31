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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CidadeCadastroService {
    private static final String MSG_CIDADE_EM_USO
            = "Cidade de código %d não pode ser removida, pois está em uso";

    private static final String MSG_CIDADE_NAO_ENCONTRADA
            = "Não existe um cadastro de cidade com código %d";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;
    @Transactional
    public Cidade salvar(Cidade cidade) {
        Long id = cidade.getEstado().getId();

        Optional<Estado> estado = estadoRepository.findById(id);
        if (estado.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de Estado com o código %d", id));
               }

        return cidadeRepository.save(cidade);
//        Estado estado = estadoRepository.findById(estadoId)
//                .orElseThrow(() -> new EntidadeNaoEncontradaException(
//                        String.format("Não existe cadastro de estado com código %d", estadoId)));
//
//        cidade.setEstado(estado);
//
//        return cidadeRepository.save(cidade);
    }
    @Transactional
    public void excluir(Long id) {
        try {
            cidadeRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cidade com código %d", id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cidade de código %d não pode ser removida, pois está em uso", id));
        }
    }

}
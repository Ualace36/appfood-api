package com.ualace.appfood.api.controller;

import com.ualace.appfood.domain.exception.EntidadeEmUsoException;
import com.ualace.appfood.domain.exception.EntidadeNaoEncontradaException;
import com.ualace.appfood.domain.model.Cidade;
import com.ualace.appfood.domain.repository.CidadeRepository;
import com.ualace.appfood.domain.repository.EstadoRepository;
import com.ualace.appfood.domain.service.CidadeCadastroService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(  "/cidades")
public class CidadeController {

    @Autowired
    private CidadeCadastroService cidadeCadastroService;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade>  obterPorId(@PathVariable Long id){
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        if (cidade.isPresent()){
            return ResponseEntity.ok(cidade.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
        try {
            cidade = cidadeCadastroService.salvar(cidade);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> atualizar(@PathVariable Long cidadeId,
                                       @RequestBody Cidade cidade) {
        try {
            // Podemos usar o orElse(null) também, que retorna a instância de cidade
            // dentro do Optional, ou null, caso ele esteja vazio,
            // mas nesse caso, temos a responsabilidade de tomar cuidado com NullPointerException
            Cidade cidadeAtual = cidadeRepository.findById(cidadeId).orElse(null);

            if (cidadeAtual != null) {
                BeanUtils.copyProperties(cidade, cidadeAtual, "cidadeId");

                cidadeAtual = cidadeCadastroService.salvar(cidadeAtual);
                return ResponseEntity.ok(cidadeAtual);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {
        try {
            cidadeCadastroService.excluir(cidadeId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}




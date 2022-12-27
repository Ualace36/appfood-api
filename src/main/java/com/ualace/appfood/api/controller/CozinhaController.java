package com.ualace.appfood.api.controller;

import com.ualace.appfood.domain.exception.EntidadeEmUsoException;
import com.ualace.appfood.domain.exception.EntidadeNaoEncontradaException;
import com.ualace.appfood.domain.model.Cozinha;
import com.ualace.appfood.domain.repository.CozinhaRepository;
import com.ualace.appfood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Object obterId(@PathVariable Long id) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
        if (cozinha.isPresent()) {
            return ResponseEntity.ok(cozinha.get());
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {

        return cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);

        if (cozinhaAtual.isPresent()) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");

            Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinhaAtual.get());
            return ResponseEntity.ok(cozinhaSalva);
        }

        return ResponseEntity.notFound().build();
    }

    //    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void remover(@PathVariable Long id) {
//
//            cadastroCozinhaService.excluir(id);
//
//
//    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            cadastroCozinhaService.excluir(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        }
    }
}

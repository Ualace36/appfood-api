package com.ualace.appfood.api.controller;

import com.ualace.appfood.domain.exception.EntidadeEmUsoException;
import com.ualace.appfood.domain.exception.EntidadeNaoEncontradaException;
import com.ualace.appfood.domain.model.Cozinha;
import com.ualace.appfood.domain.model.Estado;
import com.ualace.appfood.domain.model.Restaurante;
import com.ualace.appfood.domain.repository.EstadoRepository;
import com.ualace.appfood.domain.service.EstadoCadastroService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    EstadoCadastroService estadoCadastroService;

    @GetMapping
    public List<Estado> list(){
        return estadoRepository.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscarPorId(@PathVariable Long id){
        Estado estado = estadoRepository.buscarPorId(id);
        if (estado != null){
            return ResponseEntity.ok(estado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado) {
        return estadoCadastroService.salvar(estado);
    }

    @PutMapping("/{idEstado}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long idEstado, @RequestBody Estado estado) {
        Estado estado1 = estadoRepository.buscarPorId(idEstado);

        if (estado1 != null) {
            BeanUtils.copyProperties(estado, estado1, "idEstado");

            estado1 = estadoCadastroService.salvar(estado1);
            return ResponseEntity.ok(estado1);
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Estado> remover(@PathVariable Long id){
       try {
           estadoCadastroService.excluir(id);
           return ResponseEntity.noContent().build();
       }catch (EntidadeNaoEncontradaException e){
           return ResponseEntity.notFound().build();
       }
       catch (EntidadeEmUsoException e){
           return ResponseEntity.status(HttpStatus.CONFLICT).build();
       }
    }
}

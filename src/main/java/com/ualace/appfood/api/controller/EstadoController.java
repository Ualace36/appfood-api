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
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    EstadoCadastroService estadoCadastroService;

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscarPorId(@PathVariable Long id){
        Optional<Estado> estado = estadoRepository.findById(id);
        if (estado.isPresent()){
            return ResponseEntity.ok(estado.get());
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
    public ResponseEntity<Estado> atualizar(@RequestBody Estado estado,@PathVariable Long idEstado ) {
        Optional<Estado> estado1 = estadoRepository.findById(idEstado);

        if (estado1.isPresent()) {
            BeanUtils.copyProperties(estado, estado1.get(), "idEstado");

     Estado  estadoSalvo = estadoCadastroService.salvar(estado1.get());
            return ResponseEntity.ok(estadoSalvo);
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

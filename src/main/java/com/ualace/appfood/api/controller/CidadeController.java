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

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeCadastroService cidadeCadastroService;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.listar();
    }

    @GetMapping("/{idCidade}")
    public ResponseEntity<Cidade>  obterPorId(@PathVariable Long idCidade){
        Cidade cidade = cidadeRepository.buscarPorId(idCidade);
        if (cidade != null){
            return ResponseEntity.ok(cidade);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade){
        try {
            Cidade cidade1 = cidadeCadastroService.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cidade1);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping("/{idCidade}")
    public ResponseEntity<?> atualizar(@PathVariable Long idCidade, @RequestBody Cidade cidade){
        try {
            Cidade cidade1 = cidadeRepository.buscarPorId(idCidade);
            if (cidade1 != null) {
                BeanUtils.copyProperties(cidade, cidade1, "idCidade");


                cidade1 = cidadeCadastroService.salvar(cidade1);
                return ResponseEntity.ok(cidade1);

            }return ResponseEntity.notFound().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{idCidade}")
    public ResponseEntity<?> excluir(@PathVariable Long idCidade){
        try{
            cidadeCadastroService.excluir(idCidade);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


}

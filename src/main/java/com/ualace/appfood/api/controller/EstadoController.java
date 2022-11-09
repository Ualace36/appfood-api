package com.ualace.appfood.api.controller;

import com.ualace.appfood.domain.model.Estado;
import com.ualace.appfood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> list(){
        return estadoRepository.listar();
    }
}

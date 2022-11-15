package com.ualace.appfood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRestaurante;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private BigDecimal taxaFrete;
    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaDePagamento> formaDePagamento = new ArrayList<>();
    @Embedded
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public Cozinha getCozinha() {
        return cozinha;
    }

    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }

    public List<FormaDePagamento> getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(List<FormaDePagamento> formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return idRestaurante.equals(that.idRestaurante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRestaurante);
    }
}

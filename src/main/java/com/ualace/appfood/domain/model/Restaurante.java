package com.ualace.appfood.domain.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
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

    public Cozinha getCozinha() {
        return cozinha;
    }

    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }

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

    public Long getId() {
        return idRestaurante;
    }

    public void setId(Long id) {
        this.idRestaurante = idRestaurante;
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

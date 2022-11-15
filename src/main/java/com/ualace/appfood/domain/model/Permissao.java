package com.ualace.appfood.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermissao;

    private String nome;

    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(Long idPermissao) {
        this.idPermissao = idPermissao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permissao permissao = (Permissao) o;
        return idPermissao.equals(permissao.idPermissao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPermissao);
    }
}

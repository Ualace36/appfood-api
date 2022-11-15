package com.ualace.appfood.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class FormaDePagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forma_pagamento_id;

    private String descricao;

    public Long getForma_pagamento_id() {
        return forma_pagamento_id;
    }

    public void setForma_pagamento_id(Long forma_pagamento_id) {
        this.forma_pagamento_id = forma_pagamento_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormaDePagamento that = (FormaDePagamento) o;
        return forma_pagamento_id.equals(that.forma_pagamento_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forma_pagamento_id);
    }
}

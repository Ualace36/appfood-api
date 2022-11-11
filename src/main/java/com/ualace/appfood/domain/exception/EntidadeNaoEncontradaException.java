package com.ualace.appfood.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{
    private static final Long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}

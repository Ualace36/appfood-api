package com.ualace.appfood.domain.exception;

public class EntidadeEmUsoException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public EntidadeEmUsoException (String mensagem){
        super(mensagem);
    }
}

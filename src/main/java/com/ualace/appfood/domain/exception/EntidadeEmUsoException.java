package com.ualace.appfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public EntidadeEmUsoException (String mensagem){
        super(mensagem);
    }
}

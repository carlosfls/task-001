package com.test.cuenta_movimientos_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends Exception{

    public UnprocessableEntityException(String message) {
        super(message);
    }
}

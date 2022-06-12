package com.folcademy.Api.Exceptions;

public class ContactoExistenteException extends RuntimeException{

    public ContactoExistenteException(String message) {
        super(message);
    }
}

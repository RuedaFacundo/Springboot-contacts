package com.folcademy.Api.Exceptions;

public class ContactoInexistenteException extends RuntimeException{

    public ContactoInexistenteException(String message) {
        super(message);
    }
}

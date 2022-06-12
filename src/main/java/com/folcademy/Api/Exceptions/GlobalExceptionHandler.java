package com.folcademy.Api.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContactoExistenteException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> contactoExistenteException(HttpServletRequest req, Exception e) {
        return new ResponseEntity<ErrorMessage>(new ErrorMessage("Contacto Existente", e.getMessage(), "1", req.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ContactoInexistenteException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> contactoInexistenteException(HttpServletRequest req, Exception e) {
        return new ResponseEntity<ErrorMessage>(new ErrorMessage("Contacto Inexistente", e.getMessage(), "2", req.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> badRequestException(HttpServletRequest req, Exception e) {
        return new ResponseEntity<ErrorMessage>(new ErrorMessage("Bad Request", e.getMessage(), "3", req.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public void handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
        res.sendError(ex.getHttpStatus().value(), ex.getMessage());
    }
}

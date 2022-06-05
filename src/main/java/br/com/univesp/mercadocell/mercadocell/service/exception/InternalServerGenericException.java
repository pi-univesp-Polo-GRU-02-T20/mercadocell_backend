package br.com.univesp.mercadocell.mercadocell.service.exception;

public class InternalServerGenericException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public InternalServerGenericException(String message) {
        super(message);
    }
}

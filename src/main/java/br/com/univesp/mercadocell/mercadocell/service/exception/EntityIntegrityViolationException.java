package br.com.univesp.mercadocell.mercadocell.service.exception;

public class EntityIntegrityViolationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public EntityIntegrityViolationException(String message) {
        super(message);
    }
}

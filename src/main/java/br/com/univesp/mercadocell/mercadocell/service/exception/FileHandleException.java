package br.com.univesp.mercadocell.mercadocell.service.exception;

public class FileHandleException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public FileHandleException(String message) {
        super(message);
    }
}
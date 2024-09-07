package br.com.aval_doc.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Não encontrado");
    }
    public NotFoundException(String type) {
        super(type + " não encontrado");
    }

}

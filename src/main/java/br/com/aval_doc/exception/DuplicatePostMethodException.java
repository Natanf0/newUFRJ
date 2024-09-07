package br.com.aval_doc.exception;

public class DuplicatePostMethodException extends RuntimeException {
    public DuplicatePostMethodException(String type){
        super(type + " já existe na base de dados!");

    }
}

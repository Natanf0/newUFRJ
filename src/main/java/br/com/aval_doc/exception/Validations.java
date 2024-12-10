package br.com.aval_doc.exception;

public interface Validations {

    default void validaDRE(String dre){
        if(!(dre.matches("[1-9][0-9]{8}"))) throw new IllegalArgumentException("Insira um DRE válido!");
    }

    default void validaNome(String nome) {
        if (nome.length() > 80) throw new IllegalArgumentException("Nome muito longo!");
        if (!(nome.toLowerCase().matches("([a-z] )*"))){
            throw new IllegalArgumentException("Insira um Nome válido!");
        }
    }

    default void validaEmail(String email){
        if(email.length()>80) throw new IllegalArgumentException("E-mail muito longo!"); // Poderia validar tamamho pelo DTO, mas preferi fazer aqui
        if(!(email.toLowerCase().matches("[a-z]*@ufrj.br") ||
                email.toLowerCase().matches("[a-z]*@[a-z]*.ufrj.br"))){ // Não é a validação mais assertiva, mas a mais eficiente no momento
            throw new IllegalArgumentException("Insira um E-mail institucional válido!");
        }
    }

    default void validaMetricasAvaliacao(short metrica, String campo){
        // Método que valida as métricas como didática, decoro, atrasos e etc
        if(metrica <0 || metrica > 10) throw new IllegalArgumentException(campo + ": Informe um valor entre zero e dez!");
    }

    default void validaDescricao(String descricao){
        if(descricao.isBlank()) throw new IllegalArgumentException("Informe a Descrição");
        if(descricao.length()>255) throw new IllegalArgumentException("Descricao muito longa!");
    }
}

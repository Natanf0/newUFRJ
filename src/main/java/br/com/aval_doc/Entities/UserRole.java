package br.com.aval_doc.Entities;
public enum UserRole {
    ALUNO("ALUNO"),
    COORDENACAO("COORDENACAO");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
package br.com.aval_doc.DTOs;

import br.com.aval_doc.Entities.Aluno;
import br.com.aval_doc.Entities.Curso;

public record AlunoDetailsDTO(String DRE,
                              String nome,
                              String emailInst,
                              String cursoNome){

    public static AlunoDetailsDTO convertAlunoToAlunoDetailsDTO(Aluno aluno, Curso curso) {
        return new AlunoDetailsDTO(aluno.getDRE(),
                aluno.getNome(),
                aluno.getEmailInst(),
                curso.getNome());
    }
}


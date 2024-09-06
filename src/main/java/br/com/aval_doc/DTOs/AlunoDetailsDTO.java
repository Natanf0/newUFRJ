package br.com.aval_doc.DTOs;

import br.com.aval_doc.Entities.Aluno;
import br.com.aval_doc.Entities.Curso;

public record AlunoDetailsDTO(String DRE,
                              String nome,
                              String emailInst,
                              Curso curso){

    public static AlunoDetailsDTO createDTO(Aluno aluno) {
        return new AlunoDetailsDTO(aluno.getDRE(),
                aluno.getNome(),aluno.
                getEmailInst(),
                aluno.getCurso());
    }
}


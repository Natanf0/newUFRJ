package br.com.aval_doc.DTOs;

import lombok.Getter;

public record AvaliacaoDTO(@Getter String descricao,
                           @Getter short didatica,
                           @Getter short atrasos,
                           @Getter short metodoAvaliacao,
                           @Getter short decoro,
                           @Getter int fk_aluno,
                           @Getter int fk_professor,
                           @Getter String fk_disciplina) {
}

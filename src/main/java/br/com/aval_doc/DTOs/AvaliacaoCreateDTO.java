package br.com.aval_doc.DTOs;

import br.com.aval_doc.Entities.Avaliacao;
import lombok.Getter;

import java.time.LocalDate;

public record AvaliacaoCreateDTO(@Getter String descricao,
                                 @Getter short didatica,
                                 @Getter short atrasos,
                                 @Getter short metodoAvaliacao,
                                 @Getter short decoro,
                                 @Getter Long fk_aluno,
                                 @Getter int fk_professor,
                                 @Getter String fk_disciplina,
                                 @Getter LocalDate dataAvaliacao) {


    public static AvaliacaoCreateDTO convertAvaliacaoToAvaliacaoCreateDTO(Avaliacao avaliacao) {
        return new AvaliacaoCreateDTO(avaliacao.getDescricao(),
                avaliacao.getDidatica(),
                avaliacao.getAtrasos(),
                avaliacao.getMetodoAvaliacao(),
                avaliacao.getDecoro(),
                avaliacao.getAluno().getId(),
                avaliacao.getProfessor().getId(),
                avaliacao.getDisciplina().getCodigo(),
                avaliacao.getDataAvaliacao()
        );

    }
}

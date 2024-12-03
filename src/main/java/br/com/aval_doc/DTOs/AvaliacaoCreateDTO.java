package br.com.aval_doc.DTOs;

import br.com.aval_doc.Entities.Avaliacao;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

public record AvaliacaoCreateDTO(@NotBlank @Getter String descricao,
                                 @NotEmpty @Min(value = 0, message = "Informe um valor entre 0 e 10") @Max(value = 10, message = "Informe um valor entre 0 e 10") @Getter
                                 short didatica,
                                 @NotEmpty @Min(value = 0, message = "Informe um valor entre 0 e 10") @Max(value = 10, message = "Informe um valor entre 0 e 10") @Getter
                                 short atrasos,
                                 @NotEmpty @Min(value = 0, message = "Informe um valor entre 0 e 10") @Max(value = 10, message = "Informe um valor entre 0 e 10") @Getter
                                 short metodoAvaliacao,
                                 @NotEmpty @Min(value = 0, message = "Informe um valor entre 0 e 10") @Max(value = 10, message = "Informe um valor entre 0 e 10") @Getter
                                 short decoro,
                                 @NotEmpty @Getter
                                 Long fk_aluno,
                                 @NotEmpty @Getter
                                 int fk_professor,
                                 @NotBlank @Getter String fk_disciplina,

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

package br.com.aval_doc.DTOs;
import jakarta.validation.constraints.*;
import lombok.Getter;
import java.time.LocalDate;

public record AvaliacaoCreateDTO(@Getter String descricao,
                                 @Getter
                                 short didatica,
                                 @Getter
                                 short atrasos,
                                 @Getter
                                 short metodoAvaliacao,
                                 @Getter
                                 short decoro,
                                 @Getter
                                 Long fk_aluno,
                                 @Getter
                                 int fk_professor,
                                 @NotBlank @Getter String fk_disciplina,
                                 @Getter LocalDate dataAvaliacao) {
}

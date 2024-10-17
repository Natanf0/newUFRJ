package br.com.aval_doc.DTOs;

import br.com.aval_doc.Entities.Avaliacao;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Optional;

public record AvaliacaoUpdateDTO(
        @Getter String descricao,
        @Getter short didatica,
        @Getter short atrasos,
        @Getter short metodoAvaliacao,
        @Getter short decoro
) {

}

package br.com.aval_doc.DTOs;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.Optional;

public record AvaliacaoUpdateDTO(
        @Getter Optional<String> descricao,
        @Getter Optional<Short> didatica,
        @Getter Optional<Short> atrasos,
        @Getter Optional<Short> metodoAvaliacao,
        @Getter Optional<Short> decoro
) {


}


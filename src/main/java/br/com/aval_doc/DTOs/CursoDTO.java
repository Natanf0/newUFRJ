package br.com.aval_doc.DTOs;

import lombok.Getter;

public record CursoDTO(
                       @Getter String nome,
                       @Getter int fk_intEscFacul) {
}

package br.com.aval_doc.DTOs;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "Informe o DRE") String DRE,
        @NotBlank(message = "Informe o DRE") String senha) {
}

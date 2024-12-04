package br.com.aval_doc.DTOs;
import jakarta.validation.constraints.*;
import lombok.Getter;

public record AvaliacaoUpdateDTO(
        @NotBlank @Getter String descricao,
        @NotEmpty @Min(value = 0, message = "Informe um valor entre 0 e 10") @Max(value = 10, message = "Informe um valor entre 0 e 10") @Getter
        short didatica,
        @NotEmpty @Min(value = 0, message = "Informe um valor entre 0 e 10") @Max(value = 10, message = "Informe um valor entre 0 e 10") @Getter
        short atrasos,
        @NotEmpty @Min(value = 0, message = "Informe um valor entre 0 e 10") @Max(value = 10, message = "Informe um valor entre 0 e 10") @Getter
        short metodoAvaliacao,
        @NotEmpty @Min(value = 0, message = "Informe um valor entre 0 e 10") @Max(value = 10, message = "Informe um valor entre 0 e 10") @Getter
        short decoro
) {


}


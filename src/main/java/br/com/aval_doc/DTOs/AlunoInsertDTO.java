package br.com.aval_doc.DTOs;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public record AlunoInsertDTO(@NotBlank(message = "Informe o DRE") @Getter String DRE,
                             @NotBlank(message = "Informe o Nome") @Getter String Nome,
                             @NotBlank(message = "Informe o E-mail Institucional") @Getter String Email_Inst,
                             @NotBlank(message = "Informe a senha") @Getter String Senha,
                             @Getter int fk_curso) {
}

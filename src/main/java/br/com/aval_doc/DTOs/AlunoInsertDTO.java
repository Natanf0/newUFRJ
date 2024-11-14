package br.com.aval_doc.DTOs;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public record AlunoInsertDTO(@Getter String DRE,
                             @Getter String Nome,
                             @Getter String Email_Inst,
                             @Getter String Senha,
                             @Getter int fk_curso) {
}

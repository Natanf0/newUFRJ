package br.com.aval_doc.DTOs;
import lombok.Getter;

public record AlunoInsertDTO(@Getter String DRE,
                             @Getter String Nome,
                             @Getter String Email_Inst,
                             @Getter String Senha,
                             @Getter int fk_curso) {
}

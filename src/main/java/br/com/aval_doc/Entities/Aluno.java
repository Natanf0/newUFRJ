package br.com.aval_doc.Entities;

import br.com.aval_doc.DTOs.AlunoInsertDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Aluno {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String DRE, nome, emailInst, senha;

    @ManyToOne
    @JoinColumn(name = "fk_curso") @Getter @Setter
    private Curso curso;

    public Aluno(AlunoInsertDTO alunoInsertDTO, Curso curso) {
        this.DRE= alunoInsertDTO.getDRE();
        this.nome = alunoInsertDTO.getNome();
        this.emailInst = alunoInsertDTO.getEmail_Inst();
        this.senha = alunoInsertDTO.getSenha();
        this.curso=curso;
    }
}

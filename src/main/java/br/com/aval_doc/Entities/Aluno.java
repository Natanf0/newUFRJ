package br.com.aval_doc.Entities;

import br.com.aval_doc.DTOs.AlunoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @JoinColumn(name = "fk_curso")
    private Curso curso;

    public Aluno(AlunoDTO alunoDTO, Curso curso) {
        this.DRE=alunoDTO.getDRE();
        this.nome = alunoDTO.getNome();
        this.emailInst = alunoDTO.getEmail_Inst();
        this.senha = alunoDTO.getSenha();
        this.curso=curso;
    }
}

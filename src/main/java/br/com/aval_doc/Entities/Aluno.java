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
    private Long ID;
    @Getter
    private String DRE,Nome, Email_Inst, Senha;
    @ManyToOne
    @JoinColumn(name = "fk_curso")
    private Curso curso;


    public Aluno(AlunoDTO alunoDTO, Curso curso) {
        this.DRE=alunoDTO.getDRE();
        this.Nome= alunoDTO.getNome();
        this.Email_Inst= alunoDTO.getEmail_Inst();
        this.Senha= alunoDTO.getSenha();
        this.curso=curso;
    }
}

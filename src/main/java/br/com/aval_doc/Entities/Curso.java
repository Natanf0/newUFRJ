package br.com.aval_doc.Entities;

import br.com.aval_doc.DTOs.CursoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name ="Curso")

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Curso {
    @Id
    private int id;

    private String nome;
    @ManyToOne
    @JoinColumn(name = "FK_IntEscFacul")
    private InstitutoEscolaFaculdade FK_IntEscFacul;

    public Curso(CursoDTO cursoDTO,InstitutoEscolaFaculdade institutoEscolaFaculdade) {
        this.nome = cursoDTO.getNome();
        this.FK_IntEscFacul = institutoEscolaFaculdade;
    }
}

package br.com.aval_doc.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Curso {
    @Id @Getter
    private int id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "FK_IntEscFacul")
    private InstitutoEscolaFaculdade FK_IntEscFacul;

}

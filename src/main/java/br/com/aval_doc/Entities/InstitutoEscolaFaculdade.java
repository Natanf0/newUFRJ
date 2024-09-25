package br.com.aval_doc.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "InstEscFacul")
@NoArgsConstructor
@AllArgsConstructor

public class InstitutoEscolaFaculdade {
    @Id
    private int id;
    private String sigla, email, URLWebsite;
    @Getter
    private String nome;
    @ManyToOne
    @JoinColumn(name = "FK_Centro")
    private Centro centro;
}

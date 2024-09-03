package br.com.aval_doc.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Professor {
    @Id
    private int id;
    private String nome, email, URL_website;
    @ManyToOne
    private InstitutoEscolaFaculdade institutoEscolaFaculdade;
}

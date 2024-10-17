package br.com.aval_doc.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Professor {
    @Id @Getter
    private int id;
    @Getter
    private String nome, email, URL_website;
    @ManyToOne
    private InstitutoEscolaFaculdade institutoEscolaFaculdade;
}

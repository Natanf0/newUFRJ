package br.com.aval_doc.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Centro")
@NoArgsConstructor
@AllArgsConstructor
public class Centro {
    @Id
    private int id;
    private String sigla, nome;

}

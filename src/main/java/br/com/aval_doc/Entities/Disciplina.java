package br.com.aval_doc.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
public class Disciplina{
    @Id @Getter
    private String codigo;
    @Getter
    private String nome;
}

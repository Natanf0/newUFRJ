package br.com.aval_doc.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
public class Disciplina{
    @Id @Getter
    private String Codigo;
    @Getter
    private String Nome;
}

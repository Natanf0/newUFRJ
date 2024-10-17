package br.com.aval_doc.Entities;
import br.com.aval_doc.DTOs.AvaliacaoCreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class Avaliacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter private String descricao;
    @Setter private short didatica;
    @Setter private short atrasos;
    @Setter private short metodoAvaliacao;
    @Setter private short decoro;

    @ManyToOne @JoinColumn(name = "fk_aluno")
    Aluno aluno;
    @ManyToOne @JoinColumn(name = "fk_professor")
    Professor professor;
    @ManyToOne @JoinColumn(name = "fk_disciplina")
    Disciplina disciplina;

    private LocalDate dataAvaliacao;

    public Avaliacao(AvaliacaoCreateDTO avaliacaoCreateDTO, Aluno aluno, Professor professor, Disciplina disciplina) {
        this.descricao = avaliacaoCreateDTO.getDescricao();
        this.didatica = avaliacaoCreateDTO.getDidatica();
        this.atrasos = avaliacaoCreateDTO.getAtrasos();
        this.metodoAvaliacao= avaliacaoCreateDTO.getMetodoAvaliacao();
        this.decoro = avaliacaoCreateDTO.getDecoro();
        this.aluno = aluno;
        this.professor = professor;
        this.disciplina = disciplina;
        this.dataAvaliacao= LocalDate.parse(LocalDate.now().toString().replace("-","/"),DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}

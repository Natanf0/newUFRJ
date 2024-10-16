package br.com.aval_doc.Entities;
import br.com.aval_doc.DTOs.AvaliacaoDTO;
import br.com.aval_doc.Services.AvaliacaoService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class Avaliacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private short didatica;
    private short atrasos;
    private short metodoAvaliacao;
    private short decoro;

    @ManyToOne @JoinColumn(name = "fk_aluno")
    Aluno aluno;
    @ManyToOne @JoinColumn(name = "fk_professor")
    Professor professor;
    @ManyToOne @JoinColumn(name = "fk_disciplina")
    Disciplina disciplina;

    private LocalDate dataAvaliacao;

    public Avaliacao(AvaliacaoDTO avaliacaoDTO, Aluno aluno, Professor professor, Disciplina disciplina) {
        this.descricao = avaliacaoDTO.getDescricao();
        this.didatica = avaliacaoDTO.getDidatica();
        this.atrasos = avaliacaoDTO.getAtrasos();
        this.metodoAvaliacao=avaliacaoDTO.getMetodoAvaliacao();
        this.decoro = avaliacaoDTO.getDecoro();
        this.aluno = aluno;
        this.professor = professor;
        this.disciplina = disciplina;
        this.dataAvaliacao= LocalDate.parse(LocalDate.now().toString().replace("-","/"),DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}

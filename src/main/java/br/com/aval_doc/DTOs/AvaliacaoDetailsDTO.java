package br.com.aval_doc.DTOs;

import br.com.aval_doc.Entities.Aluno;
import br.com.aval_doc.Entities.Avaliacao;
import br.com.aval_doc.Entities.Disciplina;
import br.com.aval_doc.Entities.Professor;
import lombok.Getter;

import java.time.LocalDate;

public record AvaliacaoDetailsDTO(@Getter String descricao,
                                  @Getter short didatica,
                                  @Getter short atrasos,
                                  @Getter short metodoAvaliacao,
                                  @Getter short decoro,
                                  @Getter String aluno,
                                  @Getter String professor,
                                  @Getter String disciplina,
                                  @Getter LocalDate dataAvaliacao) {

    public static AvaliacaoDetailsDTO convertAvaliacaoToAvaliacaoDetailsDTO(Avaliacao avaliacao, Aluno aluno, Professor professor, Disciplina disciplina) {
        return new AvaliacaoDetailsDTO(avaliacao.getDescricao(),
                avaliacao.getDidatica(),
                avaliacao.getAtrasos(),
                avaliacao.getMetodoAvaliacao(),
                avaliacao.getDecoro(),
                aluno.getNome(),
                professor.getNome(),
                disciplina.getCodigo() +" - "+ disciplina.getNome(),
                avaliacao.getDataAvaliacao()
        );
    }

}
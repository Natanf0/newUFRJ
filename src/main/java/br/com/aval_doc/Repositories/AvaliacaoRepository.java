package br.com.aval_doc.Repositories;

import br.com.aval_doc.Entities.Aluno;
import br.com.aval_doc.Entities.Avaliacao;
import br.com.aval_doc.Entities.Disciplina;
import br.com.aval_doc.Entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("FROM Avaliacao A WHERE A.aluno=:fk_aluno AND A.professor=:fk_professor AND A.disciplina=:fk_disciplina")
    boolean validaAvaliacaoDuplicada(int fk_aluno, int fk_professor,String fk_disciplina);
}

package br.com.aval_doc.Repositories;

import br.com.aval_doc.Entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("FROM Avaliacao A  WHERE A.aluno.id=:fk_aluno AND A.professor.id=:fk_professor " +
            "AND A.disciplina.codigo=:fk_disciplina AND DATEDIFF(CURRENT DATE ,A.dataAvaliacao)<90 SELECT COUNT(*)")
    int validaAvaliacaoDuplicada(Long fk_aluno, int fk_professor,String fk_disciplina);

    Avaliacao findAvaliacaoById(Long id);

}

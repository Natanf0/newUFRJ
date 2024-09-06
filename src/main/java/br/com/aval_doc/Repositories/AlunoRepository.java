package br.com.aval_doc.Repositories;

import br.com.aval_doc.Entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    Aluno findById(int id);
    boolean existsAlunoByDRE(String DRE);
}

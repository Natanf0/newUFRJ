package br.com.aval_doc.Repositories;

import br.com.aval_doc.Entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
}

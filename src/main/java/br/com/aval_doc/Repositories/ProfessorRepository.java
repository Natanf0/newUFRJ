package br.com.aval_doc.Repositories;

import br.com.aval_doc.Entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}

package br.com.aval_doc.Repositories;

import br.com.aval_doc.Entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {

    Curso findCursoById(int id);
}

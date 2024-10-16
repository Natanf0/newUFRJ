package br.com.aval_doc.Repositories;

import br.com.aval_doc.Entities.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DisciplinaRepository extends JpaRepository<Disciplina, String> {
    @Query("from Disciplina d where d.codigo=:codigo")
    Disciplina findByCodigo(String codigo);
    @Query("from Disciplina d where d.codigo=:codigo")
    boolean existsByCodigo(String codigo);
}

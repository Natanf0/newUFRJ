package br.com.aval_doc.Repositories;

import br.com.aval_doc.Entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    boolean existsAlunoByDRE(String DRE);
    boolean existsAlunoById(Long id);

    UserDetails findByDRE(String DRE);
}

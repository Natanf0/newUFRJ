package br.com.aval_doc.Services;

import br.com.aval_doc.DTOs.AvaliacaoDTO;
import br.com.aval_doc.Entities.Aluno;
import br.com.aval_doc.Entities.Avaliacao;
import br.com.aval_doc.Entities.Disciplina;
import br.com.aval_doc.Entities.Professor;
import br.com.aval_doc.Repositories.AlunoRepository;
import br.com.aval_doc.Repositories.AvaliacaoRepository;
import br.com.aval_doc.Repositories.DisciplinaRepository;
import br.com.aval_doc.Repositories.ProfessorRepository;
import br.com.aval_doc.exception.DuplicatePostMethodException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {
    private AvaliacaoRepository avaliacaoRepository;
    private AlunoRepository alunoRepository;
    private ProfessorRepository professorRepository;
    private DisciplinaRepository disciplinaRepository;

    private final String entityNameForException = "Avaliacao";
    public AvaliacaoService(AlunoRepository alunoRepository, ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository, AvaliacaoRepository avaliacaoRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Transactional
    public ResponseEntity<Avaliacao> create(AvaliacaoDTO avaliacaoDTO){
        if(avaliacaoRepository.validaAvaliacaoDuplicada(avaliacaoDTO.getFk_aluno(), avaliacaoDTO.getFk_professor(), avaliacaoDTO.getFk_disciplina())){
            throw new DuplicatePostMethodException(entityNameForException);
        }else {
            Aluno aluno = alunoRepository.findById(avaliacaoDTO.getFk_aluno());
            Professor professor = professorRepository.findById(avaliacaoDTO.getFk_professor());
            Disciplina disciplina = disciplinaRepository.findByCodigo(avaliacaoDTO.getFk_disciplina());
            return ResponseEntity.ok(avaliacaoRepository.save(
                    new Avaliacao(avaliacaoDTO, aluno, professor, disciplina)));
        }
    }








}

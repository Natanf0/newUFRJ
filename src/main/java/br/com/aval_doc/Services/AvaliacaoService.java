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
import br.com.aval_doc.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        if(!(alunoRepository.existsAlunoById(avaliacaoDTO.getFk_aluno()))) throw new NotFoundException("Aluno");
        if(!(professorRepository.existsById(avaliacaoDTO.fk_professor()))) throw new NotFoundException("Professor");
        if((disciplinaRepository.existsByCodigo(avaliacaoDTO.getFk_disciplina()))) throw new NotFoundException("Disciplina");
        if(avaliacaoRepository.validaAvaliacaoDuplicada(avaliacaoDTO.getFk_aluno(), avaliacaoDTO.getFk_professor(), avaliacaoDTO.getFk_disciplina())!=0){
            throw new DuplicatePostMethodException(entityNameForException);
        }
        else { return ResponseEntity.ok(avaliacaoRepository.save(
                            new Avaliacao(avaliacaoDTO,
                            alunoRepository.fetchAlunoByID(avaliacaoDTO.getFk_aluno()),
                            professorRepository.findById(avaliacaoDTO.getFk_professor()),
                            disciplinaRepository.findByCodigo(avaliacaoDTO.getFk_disciplina()))));
        }
    }
    public ResponseEntity<List<AvaliacaoDTO>> getAll(){
        return ResponseEntity.ok(avaliacaoRepository
                .findAll()
                .stream().map(AvaliacaoDTO::createDTO)
                .collect(Collectors.toList()));
    }

    public AvaliacaoDTO getById(Long id) {
        if (avaliacaoRepository.existsById(id)) {
            return AvaliacaoDTO.createDTO(avaliacaoRepository.findAvaliacaoById(id));
        } else {
            throw new NotFoundException(entityNameForException);
        }
    }


}

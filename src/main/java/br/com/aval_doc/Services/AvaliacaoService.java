package br.com.aval_doc.Services;

import br.com.aval_doc.DTOs.AvaliacaoCreateDTO;
import br.com.aval_doc.DTOs.AvaliacaoDetailsDTO;
import br.com.aval_doc.DTOs.AvaliacaoUpdateDTO;
import br.com.aval_doc.Entities.Avaliacao;
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
    public ResponseEntity<AvaliacaoDetailsDTO> create(AvaliacaoCreateDTO avaliacaoCreateDTO){
        validaAlunoExists(avaliacaoCreateDTO.getFk_aluno());
        validaProfessorExists(avaliacaoCreateDTO.getFk_professor());
        validaDisciplinaExists(avaliacaoCreateDTO.getFk_disciplina());
        validaDuplicatePostMethods(avaliacaoCreateDTO);

        Avaliacao avaliacao = new Avaliacao(avaliacaoCreateDTO, alunoRepository.findById(avaliacaoCreateDTO.getFk_aluno()).get(),
                                            professorRepository.findById(avaliacaoCreateDTO.getFk_professor()), disciplinaRepository.findByCodigo(avaliacaoCreateDTO.getFk_disciplina()));
        avaliacaoRepository.save(avaliacao);

        return ResponseEntity.ok(AvaliacaoDetailsDTO.convertAvaliacaoToAvaliacaoDetailsDTO(avaliacao,
                    alunoRepository.findById(avaliacaoCreateDTO.getFk_aluno()).get(),
                    professorRepository.findById(avaliacaoCreateDTO.getFk_professor()),
                    disciplinaRepository.findByCodigo(avaliacaoCreateDTO.getFk_disciplina())));

    }
    @Transactional
    public ResponseEntity<AvaliacaoDetailsDTO> update(Long id, AvaliacaoUpdateDTO avaliacaoUpdateDTO) {
        Avaliacao avaliacao = avaliacaoRepository.findAvaliacaoById(id);
        if (avaliacao!=null) {
            if(avaliacaoUpdateDTO.getDescricao()!=null) avaliacao.setDescricao(avaliacaoUpdateDTO.getDescricao());
            if(avaliacaoUpdateDTO.getDidatica()>0) avaliacao.setDidatica(avaliacaoUpdateDTO.getDidatica());
            if(avaliacaoUpdateDTO.getAtrasos()>0) avaliacao.setAtrasos(avaliacaoUpdateDTO.getAtrasos());
            if(avaliacaoUpdateDTO.getMetodoAvaliacao()>0) avaliacao.setMetodoAvaliacao(avaliacaoUpdateDTO.getMetodoAvaliacao());
            if(avaliacaoUpdateDTO.getDecoro()>0) avaliacao.setDecoro(avaliacaoUpdateDTO.getDecoro());
            
            return ResponseEntity.ok(AvaliacaoDetailsDTO.convertAvaliacaoToAvaliacaoDetailsDTO(avaliacao,
                                    alunoRepository.findById(avaliacao.getAluno().getId()).get(),
                                    professorRepository.findById(avaliacao.getProfessor().getId()),
                                    disciplinaRepository.findByCodigo(avaliacao.getDisciplina().getCodigo())));
        }else {
            throw new NotFoundException(entityNameForException);
        }
    }

    public ResponseEntity<AvaliacaoDetailsDTO> delete(Long id) {
        validaAvaliacaoExists(id);
        Avaliacao avaliacao = avaliacaoRepository.findAvaliacaoById(id);
        avaliacaoRepository.deleteById(id);
        return ResponseEntity.ok(AvaliacaoDetailsDTO.convertAvaliacaoToAvaliacaoDetailsDTO(avaliacao, avaliacao.getAluno(),
                                    avaliacao.getProfessor(), avaliacao.getDisciplina()));
    }

    public ResponseEntity<List<AvaliacaoDetailsDTO>> getAll(){
        return ResponseEntity.ok(avaliacaoRepository
                .findAll()
                .stream().map(e->AvaliacaoDetailsDTO.convertAvaliacaoToAvaliacaoDetailsDTO(e, e.getAluno(), e.getProfessor(), e.getDisciplina()))
                .collect(Collectors.toList()));
    }

    public ResponseEntity<AvaliacaoDetailsDTO> getById(Long id) {
        validaAvaliacaoExists(id);
        Avaliacao avaliacao = avaliacaoRepository.findAvaliacaoById(id);
        return ResponseEntity.ok(AvaliacaoDetailsDTO.convertAvaliacaoToAvaliacaoDetailsDTO(avaliacao, avaliacao.getAluno(), avaliacao.getProfessor(), avaliacao.getDisciplina()));

    }
    private void validaAlunoExists(Long id) {
        if(!(alunoRepository.existsAlunoById(id))) throw new NotFoundException("Aluno");
    }
    private void validaProfessorExists(int id) {
        if(!(professorRepository.existsById(id))) throw new NotFoundException("Professor");
    }
    private void validaDisciplinaExists(String codigo) {
        if(disciplinaRepository.existsByCodigo(codigo)==null) throw new NotFoundException("Disciplina");
    }
    private void validaAvaliacaoExists(Long id) {
        if(!(avaliacaoRepository.existsById(id))) throw new NotFoundException("Avaliacao");
    }
    private void validaDuplicatePostMethods(AvaliacaoCreateDTO avaliacaoCreateDTO) {
        if(avaliacaoRepository.validaAvaliacaoDuplicada(avaliacaoCreateDTO.getFk_aluno(), avaliacaoCreateDTO.getFk_professor(), avaliacaoCreateDTO.getFk_disciplina())!=0){
            throw new DuplicatePostMethodException(entityNameForException);
        }
    }
}

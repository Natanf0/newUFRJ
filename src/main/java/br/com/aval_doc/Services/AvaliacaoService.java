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

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
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
        if(!(alunoRepository.existsAlunoById(avaliacaoCreateDTO.getFk_aluno()))) throw new NotFoundException("Aluno");
        if(!(professorRepository.existsById(avaliacaoCreateDTO.fk_professor()))) throw new NotFoundException("Professor");
        if(disciplinaRepository.existsByCodigo(avaliacaoCreateDTO.getFk_disciplina())==null) throw new NotFoundException("Disciplina");
        if(avaliacaoRepository.validaAvaliacaoDuplicada(avaliacaoCreateDTO.getFk_aluno(), avaliacaoCreateDTO.getFk_professor(), avaliacaoCreateDTO.getFk_disciplina())!=0){
            throw new DuplicatePostMethodException(entityNameForException);
        }
        else {
            Avaliacao avaliacao = new Avaliacao(avaliacaoCreateDTO,
                                            alunoRepository.fetchAlunoByID(avaliacaoCreateDTO.getFk_aluno()),
                                            professorRepository.findById(avaliacaoCreateDTO.getFk_professor()),
                                            disciplinaRepository.findByCodigo(avaliacaoCreateDTO.getFk_disciplina()));
            avaliacaoRepository.save(avaliacao);
            return ResponseEntity.ok(AvaliacaoDetailsDTO.convertAvaliacaoToAvaliacaoDetailsDTO(avaliacao,
                    alunoRepository.fetchAlunoByID(avaliacaoCreateDTO.getFk_aluno()),
                    professorRepository.findById(avaliacaoCreateDTO.getFk_professor()),
                    disciplinaRepository.findByCodigo(avaliacaoCreateDTO.getFk_disciplina())));
        }
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
            //return ResponseEntity.ok(patcher(id, avaliacaoUpdateDTO));
            return ResponseEntity.ok(AvaliacaoDetailsDTO.convertAvaliacaoToAvaliacaoDetailsDTO(avaliacao,
                                    alunoRepository.fetchAlunoByID(avaliacao.getAluno().getId()),
                                    professorRepository.findById(avaliacao.getProfessor().getId()),
                                    disciplinaRepository.findByCodigo(avaliacao.getDisciplina().getCodigo())));
        }else {
            throw new NotFoundException(entityNameForException);
        }
    }

//    private AvaliacaoDetailsDTO patcher(Long id, AvaliacaoUpdateDTO avaliacaoUpdateDTO) throws IllegalAccessException {
//        Avaliacao avaliacao = avaliacaoRepository.findAvaliacaoById(id);
//        Field[] fieldsAvaliacaoUpdate = AvaliacaoUpdateDTO.class.getDeclaredFields();
//
//        for(Field field: fieldsAvaliacaoUpdate){
//            field.setAccessible(true);
//            Object value = field.get(avaliacaoUpdateDTO);
//            if(value!=null){
//                field.set(avaliacao,value);
//            }
//            //MAKE THE FIELD PRIVATE AGAIN
//            field.setAccessible(false);
//        }
//        return AvaliacaoDetailsDTO.convertAvaliacaoToAvaliacaoDetailsDTO(avaliacao,
//                alunoRepository.fetchAlunoByID(avaliacao.getAluno().getId()),
//                professorRepository.findById(avaliacao.getProfessor().getId()),
//                disciplinaRepository.findByCodigo(avaliacao.getDisciplina().getCodigo()));
//    }
    public ResponseEntity<List<AvaliacaoCreateDTO>> getAll(){
        return ResponseEntity.ok(avaliacaoRepository
                .findAll()
                .stream().map(AvaliacaoCreateDTO::convertAvaliacaoToAvaliacaoCreateDTO)
                .collect(Collectors.toList()));
    }

    public AvaliacaoCreateDTO getById(Long id) {
        if (avaliacaoRepository.existsById(id)) {
            return AvaliacaoCreateDTO.convertAvaliacaoToAvaliacaoCreateDTO(avaliacaoRepository.findAvaliacaoById(id));
        } else {
            throw new NotFoundException(entityNameForException);
        }
    }


}

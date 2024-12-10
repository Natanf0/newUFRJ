package br.com.aval_doc.Services;

import br.com.aval_doc.DTOs.AlunoDetailsDTO;
import br.com.aval_doc.DTOs.AlunoInsertDTO;
import br.com.aval_doc.Entities.Aluno;
import br.com.aval_doc.Entities.Curso;
import br.com.aval_doc.Repositories.AlunoRepository;
import br.com.aval_doc.Repositories.CursoRepository;
import br.com.aval_doc.exception.DuplicatePostMethodException;
import br.com.aval_doc.exception.NotFoundException;
import br.com.aval_doc.exception.Validations;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AlunoService implements Validations {
    private AlunoRepository alunoRepository;
    private CursoRepository cursoRepository;

    private final String entityNameForException = "Aluno";

    public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    public AlunoDetailsDTO fetchById(Long id){
        validaAlunoExists(id);
        Aluno aluno = alunoRepository.findById(id).get();
        Curso curso = cursoRepository.findCursoById(aluno.getCurso().getId());
        System.out.println(aluno.getRole());
        return AlunoDetailsDTO.convertAlunoToAlunoDetailsDTO(aluno, curso);
    }

    public List<AlunoDetailsDTO> fetchAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return alunoRepository.findAll(pageable).stream()
                .map(e->AlunoDetailsDTO.convertAlunoToAlunoDetailsDTO(e, e.getCurso()))
                .collect(Collectors.toList());
    }

    @Transactional
    public AlunoDetailsDTO create(AlunoInsertDTO alunoInsertDTO) {
        if(!(alunoRepository.existsAlunoByDRE(alunoInsertDTO.getDRE()))) {
            validaDRE(alunoInsertDTO.getDRE());
            validaNome(alunoInsertDTO.getNome());
            validaEmail(alunoInsertDTO.Email_Inst());

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Aluno aluno = new Aluno(alunoInsertDTO, cursoRepository.findCursoById(alunoInsertDTO.getFk_curso()));
            aluno.setSenha(encoder.encode(aluno.getSenha()));
            alunoRepository.save(aluno);
            return AlunoDetailsDTO.convertAlunoToAlunoDetailsDTO(aluno, cursoRepository.findCursoById(alunoInsertDTO.getFk_curso()));
        } else {
            throw new DuplicatePostMethodException(entityNameForException);
        }
    }

    public ResponseEntity<AlunoDetailsDTO> deleteById(Long id){
        validaAlunoExists(id);
        Aluno aluno = alunoRepository.findById(id).get();
        Curso curso = cursoRepository.findCursoById(aluno.getCurso().getId());
        alunoRepository.delete(aluno);
        return ResponseEntity.ok(AlunoDetailsDTO.convertAlunoToAlunoDetailsDTO(aluno,curso));
    }

    private void validaAlunoExists(Long id){
        if(!(alunoRepository.existsAlunoById(id))) throw new NotFoundException(entityNameForException);
    }

}
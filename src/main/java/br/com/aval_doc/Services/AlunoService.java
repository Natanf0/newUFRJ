package br.com.aval_doc.Services;

import br.com.aval_doc.DTOs.AlunoDetailsDTO;
import br.com.aval_doc.DTOs.AlunoInsertDTO;
import br.com.aval_doc.Entities.Aluno;
import br.com.aval_doc.Repositories.AlunoRepository;
import br.com.aval_doc.Repositories.CursoRepository;
import br.com.aval_doc.exception.DuplicatePostMethodException;
import br.com.aval_doc.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    private AlunoRepository alunoRepository;
    private CursoRepository cursoRepository;

    private final String entityNameForException = "Aluno";

    public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    public AlunoDetailsDTO fetchById(int id){
        if(alunoRepository.existsById(id)){
            Aluno aluno = alunoRepository.findById(id);
            aluno.setCurso(cursoRepository.findCursoById(aluno.getCurso().getId()));
            return AlunoDetailsDTO.createDTO(aluno);}
        else{throw new NotFoundException(entityNameForException);}
    }

    public List<AlunoDetailsDTO> fetchAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return alunoRepository.findAll(pageable).stream()
                .map(AlunoDetailsDTO::createDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AlunoDetailsDTO create(AlunoInsertDTO alunoInsertDTO) {
        if(!(alunoRepository.existsAlunoByDRE(alunoInsertDTO.getDRE()))) {
            Aluno aluno = new Aluno(alunoInsertDTO, cursoRepository.findCursoById(alunoInsertDTO.getFk_curso()));
            alunoRepository.save(aluno);
            return AlunoDetailsDTO.createDTO(aluno);
        } else {
            throw new DuplicatePostMethodException(entityNameForException);
        }
    }

    @Transactional
    public void deleteById(int id){
        if(alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
        }else{throw new NotFoundException(entityNameForException);}
    }
}

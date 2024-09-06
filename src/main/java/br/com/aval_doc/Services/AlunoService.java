package br.com.aval_doc.Services;

import br.com.aval_doc.DTOs.AlunoDetailsDTO;
import br.com.aval_doc.DTOs.AlunoInsertDTO;
import br.com.aval_doc.Entities.Aluno;
import br.com.aval_doc.Repositories.AlunoRepository;
import br.com.aval_doc.Repositories.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    private AlunoRepository alunoRepository;
    private CursoRepository cursoRepository;
    public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    public AlunoDetailsDTO fetchById(int id){
        if(alunoRepository.existsById(id)){
            Aluno aluno = alunoRepository.findById(id);
            aluno.setCurso(cursoRepository.findCursoById(aluno.getCurso().getId()));
            return AlunoDetailsDTO.createDTO(aluno);}
        else{throw new IllegalArgumentException("Id inválido");}
    }

    public List<AlunoDetailsDTO> fetchAll(){
        return alunoRepository.findAll().stream()
                .map(AlunoDetailsDTO::createDTO)
                .collect(Collectors.toList());
    }

    public AlunoDetailsDTO create(AlunoInsertDTO alunoInsertDTO) {
        if(!alunoRepository.findByDRE(alunoInsertDTO.getDRE())) {
            Aluno aluno = new Aluno(alunoInsertDTO, cursoRepository.findCursoById(alunoInsertDTO.getFk_curso()));
            alunoRepository.save(aluno);
            return AlunoDetailsDTO.createDTO(aluno);
        } else {
            throw new IllegalArgumentException("Já existe um aluno com este DRE");
        }
    }
    public void deleteById(int id){
        if(alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
        }else{throw new IllegalArgumentException("Id inválido");}
    }
}

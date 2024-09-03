package br.com.aval_doc.Controllers;

import br.com.aval_doc.DTOs.AlunoDTO;
import br.com.aval_doc.Entities.Aluno;
import br.com.aval_doc.Entities.Curso;
import br.com.aval_doc.Repositories.AlunoRepository;
import br.com.aval_doc.Repositories.CursoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class Alunocontroller {
    AlunoRepository alunoRepository;
    CursoRepository cursoRepository;
    public Alunocontroller(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository= cursoRepository;
    }
    @GetMapping
    public List<Aluno> listaAlunos(){return alunoRepository.findAll();}

    @GetMapping("/{id}")
    public Aluno fetchAluno(@PathVariable int id){
        if(alunoRepository.existsById(id)){
            return alunoRepository.findById(id);
        }else{
            throw new IllegalArgumentException("Id inválido");
        }

    }
    @PostMapping()
    public Aluno insertAluno(@RequestBody AlunoDTO alunoDTO){
        return alunoRepository.save(new Aluno(alunoDTO,
                cursoRepository.findCursoById(alunoDTO.getFk_curso())));
    }

    @DeleteMapping("/{id}")
    public void deleteAluno(@PathVariable int id){
        if(alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException("Id inválido");
        }

    }



}

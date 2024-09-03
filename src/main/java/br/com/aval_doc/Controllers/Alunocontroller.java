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

    @PostMapping()
    public Aluno insertAluno(@RequestBody AlunoDTO alunoDTO){
        System.out.println(alunoDTO.toString());
        System.out.println(cursoRepository.findCursoById(alunoDTO.getFk_curso()));
        Curso curso = cursoRepository.findCursoById(alunoDTO.getFk_curso());
        Aluno aluno = new Aluno(alunoDTO,curso);
        System.out.println(aluno.toString());
        System.out.println(curso.toString());
        return alunoRepository.save(aluno);
    }

}

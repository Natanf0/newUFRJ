package br.com.aval_doc.Controllers;


import br.com.aval_doc.Entities.Disciplina;
import br.com.aval_doc.Repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {


    DisciplinaRepository disciplinaRepository;
    public DisciplinaController(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    @GetMapping()
    public List<Disciplina> getAllDisciplinas(){
        System.out.println(disciplinaRepository.findAll());
        return disciplinaRepository.findAll();
    }
}

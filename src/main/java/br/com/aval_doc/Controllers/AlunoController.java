package br.com.aval_doc.Controllers;

import br.com.aval_doc.DTOs.AlunoDetailsDTO;
import br.com.aval_doc.DTOs.AlunoInsertDTO;
import br.com.aval_doc.Services.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ufrj_api/aluno")
public class AlunoController {
    AlunoService alunoService;
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
    @GetMapping
    public ResponseEntity<List<AlunoDetailsDTO>> listAllAlunos(@RequestParam(defaultValue ="0") int page,
                                                               @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(alunoService.fetchAll(page, size));}

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDetailsDTO> fetchAlunoById(@PathVariable int id){
       return ResponseEntity.ok(alunoService.fetchById(id));
    }
    @PostMapping()
    public ResponseEntity<AlunoDetailsDTO> insertAluno(@RequestBody AlunoInsertDTO alunoInsertDTO){
        return ResponseEntity.ok(alunoService.create(alunoInsertDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteAluno(@PathVariable int id){alunoService.deleteById(id);}

}
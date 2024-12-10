package br.com.aval_doc.Controllers;

import br.com.aval_doc.DTOs.AlunoDetailsDTO;
import br.com.aval_doc.DTOs.AlunoInsertDTO;
import br.com.aval_doc.Services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<AlunoDetailsDTO> fetchAlunoById(@PathVariable Long id){
       return ResponseEntity.ok(alunoService.fetchById(id));
    }
    @PostMapping()
    public ResponseEntity<AlunoDetailsDTO> insertAluno(@RequestBody AlunoInsertDTO alunoInsertDTO){
        return ResponseEntity.ok(alunoService.create(alunoInsertDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AlunoDetailsDTO> deleteAluno(@PathVariable Long id) {
        return alunoService.deleteById(id);
    }

}

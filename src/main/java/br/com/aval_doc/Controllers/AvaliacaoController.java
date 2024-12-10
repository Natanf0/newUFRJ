package br.com.aval_doc.Controllers;

import br.com.aval_doc.DTOs.AvaliacaoCreateDTO;
import br.com.aval_doc.DTOs.AvaliacaoDetailsDTO;
import br.com.aval_doc.DTOs.AvaliacaoUpdateDTO;
import br.com.aval_doc.Services.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ufrj_api/avaliacao")
public class AvaliacaoController {

    AvaliacaoService avaliacaoService;
    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDetailsDTO> createAvaliacao(@RequestBody AvaliacaoCreateDTO avaliacaoCreateDTO) {
        return avaliacaoService.create(avaliacaoCreateDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AvaliacaoDetailsDTO> updateAvaliacao(@PathVariable Long id, @RequestBody AvaliacaoUpdateDTO avaliacaoUpdateDTO) throws IllegalAccessException {
        return avaliacaoService.update(id, avaliacaoUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AvaliacaoDetailsDTO> deleteAvaliacao(@PathVariable Long id) {
        return avaliacaoService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDetailsDTO> getAvaliacao(@PathVariable Long id) {
        return avaliacaoService.getById(id);
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoDetailsDTO>> getAllAvaliacao() {
        return avaliacaoService.getAll();
    }


//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
}

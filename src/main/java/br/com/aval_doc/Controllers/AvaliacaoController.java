package br.com.aval_doc.Controllers;

import br.com.aval_doc.DTOs.AvaliacaoCreateDTO;
import br.com.aval_doc.DTOs.AvaliacaoDetailsDTO;
import br.com.aval_doc.DTOs.AvaliacaoUpdateDTO;
import br.com.aval_doc.Services.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ufrj_api/avaliacao")
public class AvaliacaoController {

    AvaliacaoService avaliacaoService;
    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDetailsDTO> createAvaliacao(@RequestBody @Valid AvaliacaoCreateDTO avaliacaoCreateDTO) {
        return avaliacaoService.create(avaliacaoCreateDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AvaliacaoDetailsDTO> updateAvaliacao(@PathVariable Long id, @RequestBody @Valid AvaliacaoUpdateDTO avaliacaoUpdateDTO) throws IllegalAccessException {
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
}

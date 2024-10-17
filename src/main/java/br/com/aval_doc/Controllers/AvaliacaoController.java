package br.com.aval_doc.Controllers;

import br.com.aval_doc.DTOs.AvaliacaoCreateDTO;
import br.com.aval_doc.DTOs.AvaliacaoDetailsDTO;
import br.com.aval_doc.DTOs.AvaliacaoUpdateDTO;
import br.com.aval_doc.Services.AvaliacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}

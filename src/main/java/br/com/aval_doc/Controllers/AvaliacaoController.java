package br.com.aval_doc.Controllers;

import br.com.aval_doc.DTOs.AvaliacaoDTO;
import br.com.aval_doc.Entities.Avaliacao;
import br.com.aval_doc.Repositories.AlunoRepository;
import br.com.aval_doc.Repositories.DisciplinaRepository;
import br.com.aval_doc.Repositories.ProfessorRepository;
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
    public ResponseEntity<Avaliacao> createAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        return avaliacaoService.create(avaliacaoDTO);
    }


}

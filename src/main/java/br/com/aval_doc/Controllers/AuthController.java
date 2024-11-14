package br.com.aval_doc.Controllers;

import br.com.aval_doc.DTOs.JwtDTO;
import br.com.aval_doc.DTOs.LoginDTO;
import br.com.aval_doc.Entities.Aluno;
import br.com.aval_doc.Services.AuthService;
import br.com.aval_doc.infra.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ufrj_api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private AuthService authService;

    public AuthController(AuthenticationManager authenticationManager,
                          AuthService authService,
                          TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<JwtDTO> signIn(@RequestBody LoginDTO loginDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.DRE(), loginDTO.senha());
        var authUser = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateAccessToken(((Aluno)authUser.getPrincipal()));
        return ResponseEntity.ok(new JwtDTO(token));
    }

}

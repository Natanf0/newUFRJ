package br.com.aval_doc.Services;

import br.com.aval_doc.DTOs.LoginDTO;
import br.com.aval_doc.Repositories.AlunoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {


    AlunoRepository alunoRepository;
    public AuthService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String DRE) {
        var user = alunoRepository.findByDRE(DRE);
        return user;
    }

}

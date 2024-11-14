package br.com.aval_doc.Entities;

import br.com.aval_doc.DTOs.AlunoInsertDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Aluno implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String DRE, nome, emailInst;
     @Setter private String senha;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "fk_curso")
    private Curso curso;

    public Aluno(AlunoInsertDTO alunoInsertDTO, Curso curso) {
        this.DRE= alunoInsertDTO.getDRE();
        this.nome = alunoInsertDTO.getNome();
        this.emailInst = alunoInsertDTO.getEmail_Inst();
        this.senha = alunoInsertDTO.getSenha();
        this.curso=curso;
        this.role=UserRole.ALUNO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ALUNO"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.DRE;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

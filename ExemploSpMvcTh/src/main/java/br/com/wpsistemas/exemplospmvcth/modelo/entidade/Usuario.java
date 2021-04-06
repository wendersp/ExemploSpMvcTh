
package br.com.wpsistemas.exemplospmvcth.modelo.entidade;

import br.com.wpsistemas.exemplospmvcth.modelo.uteis.TipoSexo;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author wender
 */
public class Usuario {
    
    private Long id;
    
    @NotBlank 
    @Size(min = 3, max = 50)
    private String nome;    
    private TipoSexo sexo;
    @NotNull
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataNascimento;
    @NotBlank
    @Email
    private String email;
    @Size(min = 6, max = 20)   
    private String senha;

    public Usuario() {
    }

    public Usuario(Long id, String nome, TipoSexo sexo, LocalDate dataNascimento, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoSexo getSexo() {
        return sexo;
    }

    public void setSexo(TipoSexo sexo) {
        this.sexo = sexo;
    }
    
    
}

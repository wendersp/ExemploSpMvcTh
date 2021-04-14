
package br.com.wpsistemas.exemplospmvcth.modelo.entidade;

import br.com.wpsistemas.exemplospmvcth.modelo.uteis.TipoSexo;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@Entity
@Table(name = "usuarios")
@NamedQueries({
        @NamedQuery(name = "Usuario.findByEmail",
                query = "SELECT u FROM Usuario u WHERE u.email LIKE :email")

})
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    @NotBlank 
    @Size(min = 3, max = 50)
    @Column(name = "nome", length = 100)
    private String nome;    
    @Column(name = "sexo", length = 1)
    private TipoSexo sexo;
    @NotNull
    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @NotBlank
    @Email
    @Column(name = "email", length = 100)
    private String email;
    @Size(min = 6, max = 20)   
    @Column(name = "senha", length = 20)
    private String senha;

    public Usuario() {
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
        this.nome = nome.toUpperCase();
    }

      
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}


package br.com.wpsistemas.exemplospmvcth.modelo.repository;

import br.com.wpsistemas.exemplospmvcth.modelo.entidade.Usuario;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author wender
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    public List<Usuario> findByNomeStartingWith(String nome);
    public List<Usuario> findByDataNascimentoBetween(LocalDate dataInicial, LocalDate dataFinal);
    public List<Usuario> findByEmail(@Param("email") String email);
    
}

package br.com.wpsistemas.exemplospmvcth.modelo.repository;

import br.com.wpsistemas.exemplospmvcth.modelo.entidade.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wender
 */
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
    
}

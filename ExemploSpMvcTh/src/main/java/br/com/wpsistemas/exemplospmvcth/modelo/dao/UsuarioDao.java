
package br.com.wpsistemas.exemplospmvcth.modelo.dao;

import br.com.wpsistemas.exemplospmvcth.modelo.entidade.Usuario;
import java.util.List;

/**
 *
 * @author wender
 */
public interface UsuarioDao {
    
    void incluir(Usuario usuario);
    
    void alterar(Usuario usuario);
    
    void excluir(Usuario usuario);
    
    Usuario pesquisar(Long id);
    
    List<Usuario> pesquisar(String nome);
    
    List<Usuario> listarTodos();
    
    
    
}

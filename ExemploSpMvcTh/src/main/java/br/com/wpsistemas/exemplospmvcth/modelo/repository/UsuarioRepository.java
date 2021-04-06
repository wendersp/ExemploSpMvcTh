/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpsistemas.exemplospmvcth.modelo.repository;

import br.com.wpsistemas.exemplospmvcth.modelo.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wender
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}

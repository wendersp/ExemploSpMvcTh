/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpsistemas.exemplospmvcth.modelo.dao;

import br.com.wpsistemas.exemplospmvcth.modelo.entidade.Usuario;
import br.com.wpsistemas.exemplospmvcth.modelo.uteis.TipoSexo;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wender
 */
@Repository
public class UsuarioDaoImpl implements UsuarioDao {

    private List<Usuario> listUsuarios;

    private List<Usuario> criarListUsuarios() {
        if (listUsuarios == null) {
            listUsuarios = new LinkedList<>();
            listUsuarios.add(new Usuario(1l, "JOSE DA SILVA", TipoSexo.MASCULINO, LocalDate.of(2000, 5, 10), "jose@mail.com", "1234"));
            listUsuarios.add(new Usuario(2l, "MARIA DA SILVA", TipoSexo.FEMININO, LocalDate.of(1995, 8, 25), "maria@mail.com", "1354"));
            listUsuarios.add(new Usuario(3l, "SEBASTIANA", TipoSexo.FEMININO, LocalDate.of(2001, 1, 11), "sebastiana@mail.com", "1884"));
            listUsuarios.add(new Usuario(4l, "ANTONIO JOÃO", TipoSexo.MASCULINO, LocalDate.of(2002, 7, 5), "antonio@mail.com", "4567"));
        }
        return listUsuarios;
    }

    @Override
    public void incluir(Usuario usuario) {
        if (listUsuarios == null) {
            listUsuarios = new LinkedList<>();
        }
        usuario.setId(System.currentTimeMillis());
        listUsuarios.add(usuario);
    }

    @Override
    public void alterar(Usuario usuario) {
        if (listUsuarios == null) {
            listUsuarios = new LinkedList<>();
        }
        listUsuarios.stream()
                .filter((user) -> user.getId().equals(usuario.getId()))
                .forEach((user) -> {
                    user.setNome(usuario.getNome());
                    user.setEmail(usuario.getEmail());
                    user.setSenha(usuario.getSenha());
                });
    }

    @Override
    public void excluir(Usuario usuario) {
        listUsuarios.removeIf((user) -> user.getId().equals(usuario.getId()));
    }

    @Override
    public Usuario pesquisar(Long id) {
        return listUsuarios.stream()
                .filter((user) -> user.getId().equals(id))
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Usuario> pesquisar(String nome) {

        return null;
    }

    @Override
    public List<Usuario> listarTodos() {
        return this.criarListUsuarios();
    }

}

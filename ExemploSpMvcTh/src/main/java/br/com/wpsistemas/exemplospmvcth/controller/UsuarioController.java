package br.com.wpsistemas.exemplospmvcth.controller;

import br.com.wpsistemas.exemplospmvcth.modelo.dao.UsuarioDao;
import br.com.wpsistemas.exemplospmvcth.modelo.entidade.Usuario;
import br.com.wpsistemas.exemplospmvcth.modelo.uteis.TipoSexo;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author wender
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @GetMapping(value = "listarTodos")
    public ModelAndView listaTodos(ModelMap model) {
        model.addAttribute("usuarios", usuarioDao.listarTodos());
        model.addAttribute("conteudo", "usuario/usuarioCons");
        return new ModelAndView("layout", model);
    }

    @GetMapping(value = "novo")
    public ModelAndView Novo(@ModelAttribute("usuario") Usuario usuario, ModelMap model) {
        model.addAttribute("conteudo", "usuario/usuarioFrm");
        return new ModelAndView("layout", model);
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("layout", "conteudo", "usuario/usuarioFrm");
        }
        String msg;
        if (usuario.getId() == null) {
            usuario.setId(System.currentTimeMillis());
            usuarioDao.incluir(usuario);
            msg = "Usuario salvo com sucesso...";
        } else {
            usuarioDao.alterar(usuario);
            msg = "Usuario alterado com sucesso...";
        }
        attr.addFlashAttribute("message", msg);
        return new ModelAndView("redirect:/usuario/listarTodos");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id, ModelMap model) {
        Usuario usuario = usuarioDao.pesquisar(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("conteudo", "usuario/usuarioFrm");
        return new ModelAndView("layout", model);
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        Usuario usuario = usuarioDao.pesquisar(id);
        usuarioDao.excluir(usuario);
        attr.addFlashAttribute("message", "Usuário excluído com sucesso.");
        return "redirect:/usuario/listarTodos";
    }

    @ModelAttribute("sexos")
    public TipoSexo[] tipoSexo() {
        return TipoSexo.values();
    }
}

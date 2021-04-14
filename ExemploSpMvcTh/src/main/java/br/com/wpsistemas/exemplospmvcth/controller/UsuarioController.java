package br.com.wpsistemas.exemplospmvcth.controller;

import br.com.wpsistemas.exemplospmvcth.modelo.entidade.Usuario;
import br.com.wpsistemas.exemplospmvcth.modelo.repository.UsuarioRepository;
import br.com.wpsistemas.exemplospmvcth.modelo.uteis.TipoSexo;
import java.time.LocalDate;
import java.time.Month;
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
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "listar")
    public ModelAndView listaTodos(ModelMap model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("conteudo", "usuario/usuarioCons");
        return new ModelAndView("layout", model);
    }
    
    @PostMapping(value = "nome")
    public ModelAndView findByNome(@ModelAttribute("nome") String nome, ModelMap model) {
        model.addAttribute("usuarios", usuarioRepository.findByNomeStartingWith(nome.toUpperCase()));
        model.addAttribute("conteudo", "usuario/usuarioCons");
        return new ModelAndView("layout", model);
    }
    
    @GetMapping(value = "datanascimento")
    public ModelAndView findByNome(ModelMap model) {
        model.addAttribute("usuarios", usuarioRepository.findByDataNascimentoBetween(LocalDate.of(2000, Month.JANUARY, 1), LocalDate.of(2000, Month.DECEMBER, 31)));
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
        usuarioRepository.save(usuario);                    
        attr.addFlashAttribute("message", "Usuario alterado com sucesso...");
        return new ModelAndView("redirect:/usuario/listar");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id, ModelMap model) {
        Usuario usuario = usuarioRepository.findById(id).get();
        model.addAttribute("usuario", usuario);
        model.addAttribute("conteudo", "usuario/usuarioFrm");
        return new ModelAndView("layout", model);
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {        
        usuarioRepository.deleteById(id);
        attr.addFlashAttribute("message", "Usuário excluído com sucesso.");
        return "redirect:/usuario/listar";
    }

    @ModelAttribute("sexos")
    public TipoSexo[] tipoSexo() {
        return TipoSexo.values();
    }
}

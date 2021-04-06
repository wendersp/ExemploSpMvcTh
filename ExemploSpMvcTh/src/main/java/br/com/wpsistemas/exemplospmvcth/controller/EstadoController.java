package br.com.wpsistemas.exemplospmvcth.controller;

import br.com.wpsistemas.exemplospmvcth.modelo.entidade.Estado;
import br.com.wpsistemas.exemplospmvcth.modelo.entidade.Usuario;
import br.com.wpsistemas.exemplospmvcth.modelo.repository.EstadoRepository;
import br.com.wpsistemas.exemplospmvcth.modelo.repository.UsuarioRepository;
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
@RequestMapping("/estado")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping(value = "listar")
    public ModelAndView listaTodos(ModelMap model) {
        model.addAttribute("estados", estadoRepository.findAll());
        model.addAttribute("conteudo", "estado/estadoCons");
        return new ModelAndView("layout", model);
    }

    @GetMapping(value = "novo")
    public ModelAndView Novo(@ModelAttribute("estado") Estado estado, ModelMap model) {
        model.addAttribute("conteudo", "estado/estadoFrm");
        return new ModelAndView("layout", model);
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid @ModelAttribute("estado") Estado estado, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("layout", "conteudo", "estado/estadoFrm");
        }        
        estadoRepository.save(estado);                    
        attr.addFlashAttribute("message", "Estado alterado com sucesso...");
        return new ModelAndView("redirect:/estado/listar");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id, ModelMap model) {
        Estado estado = estadoRepository.findById(id).get();
        model.addAttribute("estado", estado);
        model.addAttribute("conteudo", "estado/estadoFrm");
        return new ModelAndView("layout", model);
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {        
        estadoRepository.deleteById(id);
        attr.addFlashAttribute("message", "Estado excluído com sucesso.");
        return "redirect:/estado/listar";
    }

   
}

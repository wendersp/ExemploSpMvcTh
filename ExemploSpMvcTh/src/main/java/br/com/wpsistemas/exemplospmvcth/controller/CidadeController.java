package br.com.wpsistemas.exemplospmvcth.controller;

import br.com.wpsistemas.exemplospmvcth.controller.converter.EstadoConverter;
import br.com.wpsistemas.exemplospmvcth.modelo.entidade.Cidade;
import br.com.wpsistemas.exemplospmvcth.modelo.entidade.Estado;
import br.com.wpsistemas.exemplospmvcth.modelo.repository.CidadeRepository;
import br.com.wpsistemas.exemplospmvcth.modelo.repository.EstadoRepository;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    
    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Estado.class, new EstadoConverter(estadoRepository));
    }

    @GetMapping(value = "listar")
    public ModelAndView listaTodos(ModelMap model) {
        model.addAttribute("cidades", cidadeRepository.findAll());
        model.addAttribute("conteudo", "cidade/cidadeCons");
        return new ModelAndView("layout", model);
    }

    @GetMapping(value = "novo")
    public ModelAndView Novo(@ModelAttribute("cidade") Cidade cidade, ModelMap model) {
        model.addAttribute("estados", estadoRepository.findAll());
        model.addAttribute("conteudo", "cidade/cidadeFrm");
        return new ModelAndView("layout", model);
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid @ModelAttribute("cidade") Cidade cidade, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {            
            return new ModelAndView("layout", "conteudo", "cidade/cidadeFrm");
        }
        cidadeRepository.save(cidade);
        attr.addFlashAttribute("message", "Cidade salvo com sucesso...");
        return new ModelAndView("redirect:/cidade/listar");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id, ModelMap model) {
        Cidade cidade = cidadeRepository.findById(id).get();
        model.addAttribute("estados", estadoRepository.findAll());
        model.addAttribute("cidade", cidade);
        model.addAttribute("conteudo", "cidade/cidadeFrm");
        return new ModelAndView("layout", model);
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {        
        cidadeRepository.deleteById(id);
        attr.addFlashAttribute("message", "Cidade excluído com sucesso.");
        return "redirect:/cidade/listar";
    }

   
    
}

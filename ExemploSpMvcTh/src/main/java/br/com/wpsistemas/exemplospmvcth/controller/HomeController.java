
package br.com.wpsistemas.exemplospmvcth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author wender
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String home() {

        return "redirect:/usuario/listar";
    }
  

}

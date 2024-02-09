package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.dao.PessoaDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("pessoa")
public class PessoaController {

    PessoaDAO dao;

    public PessoaController(){
        dao = new PessoaDAO();
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("pessoas", dao.buscarPessoas());
        return new ModelAndView("/pessoa/list");
    }

}

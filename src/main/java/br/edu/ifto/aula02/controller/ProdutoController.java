package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.dao.ProdutoDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("produto")
public class ProdutoController {

    ProdutoDAO dao;

    public ProdutoController(){
        dao = new ProdutoDAO();
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("produtos", dao.buscarProdutos());
        return new ModelAndView("/produto/list"); //aponta o caminho da view no projeto em /templates/produto
    }

}

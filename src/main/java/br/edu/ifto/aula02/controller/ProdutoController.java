package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.dao.ProdutoDAO;
import br.edu.ifto.aula02.model.entity.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("produto")
public class ProdutoController {

    ProdutoDAO dao;

    public ProdutoController(){
        dao = new ProdutoDAO();
    }

    /**
     * @param produto necessário devido utilizar no form.html o th:object que faz referência ao objeto esperado no controller.
     * @return
     */
    @GetMapping("/form")
    public String form(Produto produto){
        return "/produto/form";
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("produtos", dao.buscarProdutos());
        return new ModelAndView("/produto/list", model); //Aponta o caminho da view no projeto em /templates/produto.
    }
    @PostMapping("/save")
    public ModelAndView save(Produto produto) {
        dao.save(produto);
        return new ModelAndView("redirect:/produto/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL.
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") int id){
        dao.remove(id);
        return new ModelAndView("redirect:/produto/list"); //Aponta o caminho da view no projeto em /templates/list.
    }
    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("produto", dao.buscarProduto(id));
        return new ModelAndView("/produto/form", model); // Página onde está a estrutura html a ser mostrada (templates/form).
    }

    @PostMapping("/update")
    public ModelAndView update(Produto produto) {
        dao.update(produto);
        return new ModelAndView("redirect:/produto/list"); //Aponta o caminho da view no projeto em /templates/produto.
    }
}

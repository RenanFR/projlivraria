package org.projlivraria.ctrl;

import org.projlivraria.dao.ProdutoDAO;
import org.projlivraria.mdl.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProdutosController {
	@Autowired//Será injetado pelo Spring
	private ProdutoDAO produtoDAO;
	@RequestMapping("/produtos/novo")
	public String form() {
		System.out.println("Entrando no formulário");
		return "livros/cadastroProduto";//Retorna página de acordo com a requisição na página conforme configuração do resolvedor
	}
	@RequestMapping("/produtos/cadastrar")
	public String cadastrar(Produto produto){
		System.out.println(produto);//Invoca o método toString da classe p/ exibir no log
		produtoDAO.salvar(produto);
		return "livros/resultado";
	}
}

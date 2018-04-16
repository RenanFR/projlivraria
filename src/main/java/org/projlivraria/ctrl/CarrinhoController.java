package org.projlivraria.ctrl;

import org.projlivraria.dao.ProdutoDAO;
import org.projlivraria.mdl.CarrinhoCompra;
import org.projlivraria.mdl.ItemCarrinho;
import org.projlivraria.mdl.TipoPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/carrinho")
@Controller
public class CarrinhoController {
	@Autowired
	private CarrinhoCompra carrinhoCompra;
	@Autowired
	private ProdutoDAO produtoDAO;
	@RequestMapping(value="/adc", method=RequestMethod.POST)
	public ModelAndView adicionar(String livroId, TipoPreco tipoLivro) {
		ModelAndView view = new ModelAndView("carrinho/meuCarrinho");
		ItemCarrinho itemCarrinho = new ItemCarrinho(produtoDAO.buscaPorTitulo(livroId), tipoLivro);
		carrinhoCompra.adcCarrinho(itemCarrinho);
		return view;
	}
}

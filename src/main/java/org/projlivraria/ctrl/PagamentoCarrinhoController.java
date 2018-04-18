package org.projlivraria.ctrl;

import java.util.concurrent.Callable;

import org.projlivraria.mdl.CarrinhoCompra;
import org.projlivraria.mdl.InfoPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pagamento")
public class PagamentoCarrinhoController {
	@Autowired
	private RestTemplate restTemplate;//Objeto p/ realizar requisições a serviços externos. Injetado pelo spring (Deve colocar na configuração)
	
	@Autowired
	private CarrinhoCompra carrinhoCompra;//Injetando componente customizado
	
	@RequestMapping("/realizarPagamento")
	public Callable<ModelAndView> finalizarCompra(RedirectAttributes attributes) {//Para que a requisição de pagamento seja assíncrona
		return () -> {//Implementando o retorno Callable via expressão lambda
			ModelAndView view = new ModelAndView("redirect:/produtos/lista");
			try {
				String enderecoServico = "http://book-payment.herokuapp.com/payment";
				String respostaObtida = restTemplate.postForObject(enderecoServico, new InfoPagamento(carrinhoCompra.getTotal()), String.class);
				attributes.addFlashAttribute("respostaObtida", respostaObtida);
				return view;
			} catch (HttpClientErrorException exception) {
				attributes.addFlashAttribute("erroObtido", exception.getMessage());
				return view;
			}
		};
	}
}

package org.projlivraria.ctrl;

import java.util.concurrent.Callable;

import org.projlivraria.mdl.CarrinhoCompra;
import org.projlivraria.mdl.InfoPagamento;
import org.projlivraria.mdl.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	
	@Autowired
	private MailSender mailSender;
	
	@RequestMapping("/realizarPagamento")
	public Callable<ModelAndView> finalizarCompra(@AuthenticationPrincipal Usuario usuario,
			RedirectAttributes attributes) {//Para que a requisição de pagamento seja assíncrona
		return () -> {//Implementando o retorno Callable via expressão lambda
			ModelAndView view = new ModelAndView("redirect:/produtos/lista");
			try {
				String enderecoServico = "http://book-payment.herokuapp.com/payment";
				String respostaObtida = restTemplate.postForObject(enderecoServico, new InfoPagamento(carrinhoCompra.getTotal()), String.class);
				attributes.addFlashAttribute("respostaObtida", respostaObtida);
				enviarEmail(usuario);
				return view;
			} catch (HttpClientErrorException exception) {
				attributes.addFlashAttribute("erroObtido", exception.getMessage());
				return view;
			}
		};
	}
	private void enviarEmail(Usuario usuario) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("Nova compra");
//		message.setTo(usuario.getUsername());//Deve ser um e-mail nesse caso
		message.setTo("renan1047@hotmail.com");
		message.setText("Compra realizada no valor de " + carrinhoCompra.getTotal());
		message.setFrom("renanfr1047@gmail.com");
		mailSender.send(message);
	}	
}

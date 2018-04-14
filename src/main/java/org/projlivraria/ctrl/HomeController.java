package org.projlivraria.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//Informa que a classe trata requisições, é uma controladora MVC
public class HomeController {
	@RequestMapping("/home")//Informa o caminho para determinada requisição
	public String index(){
		System.out.println("Acessando método inicial. /home");
		return "inicio";//Visualização retornada de acordo com padrão configurado
	}
}

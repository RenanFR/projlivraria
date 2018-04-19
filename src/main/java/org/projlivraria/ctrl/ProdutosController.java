package org.projlivraria.ctrl;

import javax.validation.Valid;

import org.projlivraria.dao.ProdutoDAO;
import org.projlivraria.mdl.Produto;
import org.projlivraria.mdl.TipoPreco;
import org.projlivraria.util.SalvadorArquivo;
import org.projlivraria.valid.ProdutoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")//Caminho base da controladora
@Scope(value=WebApplicationContext.SCOPE_REQUEST)//Os dados enviados/recebidos pela controladora tem escopo requisição
public class ProdutosController {
	@Autowired//Será injetado pelo Spring
	private ProdutoDAO produtoDAO;
	@Autowired
	private SalvadorArquivo salvadorArquivo;
	
	@RequestMapping("/novo")
	public ModelAndView form(Produto produto) {//Ao usar o form:form é necessário enviar o objeto a ser persistido
		ModelAndView modelAndView = new ModelAndView("livros/cadastroProduto");//Objeto p/ adicionar valores p/ consumo na visão
		modelAndView.addObject("tiposPreco", TipoPreco.values());//Adiciona chave/valor p/ página. Poderemos consumir via expressão
		return modelAndView;//Retorna página de acordo com a requisição na página conforme configuração do resolvedor
	}
	@RequestMapping(value="/cadastrar", method=RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Produto produto, BindingResult result,//Anotação habilita validação do tipo. 
			MultipartFile sumario,//Permite receber um arquivo
			//BindingResult trará se houveram ou não erros, e os retornará (Deve vir após a entidade validada)
			RedirectAttributes attributes){//Permite enviar atributos de requisição p/ outra
		System.out.println(produto);//Invoca o método toString da classe p/ exibir no log
		if(result.hasErrors()){//Nossa implementação do validador irá alimentar os erros no BindingResult
			return form(produto);
		}
		String caminhoSumarioServidor = salvadorArquivo.escreve("caminhoBase", sumario);//Utilizando o componente injetado
		produto.setCaminhoSumario(caminhoSumarioServidor);//Campo irá referenciar o caminho do arquivo no servidor
		produtoDAO.salvar(produto);
		attributes.addFlashAttribute("mensagem", "O livro foi inserido.");//Adicionando um atributo para utilização na próxima requisição
		return new ModelAndView("redirect:lista");//Usando essa sintaxe enviamos para outro método após execução deste
	}
	@InitBinder//Serve para realizar configurações iniciais p/ controladora
	public void initBinder(WebDataBinder dataBinder) {//Isso é feito por meio do objeto em parâmetro
		dataBinder.addValidators(new ProdutoValidator());
	}
	
	@RequestMapping(value="/lista", method=RequestMethod.GET)
//	@ResponseBody//Irá retornar resposta serializada no corpo da resposta. Deve ter serializador como dependência do projeto
	public ModelAndView listaLivros(){
		ModelAndView modelAndView = new ModelAndView("livros/livros");
		modelAndView.addObject("todosLivros", produtoDAO.todos());
		return modelAndView;
	}
	
	@RequestMapping(value="/detalhes/{nomeLivro}")//Podemos receber um atributo dinâmico na URL
	public ModelAndView detalhesLivro(@PathVariable String nomeLivro) {//	@PathVariable. Liga o valor da URL c/ parâmetro do método
		ModelAndView view = new ModelAndView("livros/detalhes");
		view.addObject("livroEncontrado", produtoDAO.livroPorNome(nomeLivro));
		return view;
	}
}

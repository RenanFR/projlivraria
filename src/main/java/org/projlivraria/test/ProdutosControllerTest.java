/**
 * @author renanfr
 *
 */
package org.projlivraria.test;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.projlivraria.cfg.ConfigJPA;
import org.projlivraria.cfg.ConfigSeguranca;
import org.projlivraria.cfg.ConfiguracaoAplicacao;
import org.projlivraria.cfg.DispatcherServlet;
import org.projlivraria.cfg.FonteDadosHomologacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@RunWith(SpringJUnit4ClassRunner.class)//Irá possibilitar execução dos testes integrado com o contexto do framework
@WebAppConfiguration//Carrega contexto/configurações Mvc da aplicação
@ContextConfiguration(classes= {ConfigJPA.class, DispatcherServlet.class, 
		ConfiguracaoAplicacao.class, FonteDadosHomologacao.class, ConfigSeguranca.class})
//As classes de configuração que devem ser lidas p/ o teste
@ActiveProfiles("homologacao")//Para que utilize a fonte de dados de homologação
public class ProdutosControllerTest {
	
	@Autowired
	private WebApplicationContext context;//Injeção do contexto da aplicação
	private MockMvc mockMvc;//Simula o ambiente Mvc da aplicação fora do servidor
	@Autowired
	private Filter filter;//Para adicionar o filtro de segurança
	
	@Before//Executado antes dos testes para inicializar algo
	public void preparaTeste() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(filter).build();//Inicializa com os parâmetros do contexto da aplicação
	}
	
	@Test
	public void listaDeProdutosCarregada() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/produtos/lista"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("todosLivros"))//Verifica se determinado atributo foi adicionado
			.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/paginas/inicio.jsp"));//Verifica se redirecionou
	}
	
	@Test
	public void controleDeAcessoCadastroLivro() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/produtos/novo")
				.with(SecurityMockMvcRequestPostProcessors.user("andre").password("andre").roles("USR")))//Da biblioteca de testes de segurança
			.andExpect(MockMvcResultMatchers.status().is(403));//Valida o código de resposta retornado
	}	
	
}

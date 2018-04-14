package org.projlivraria.cfg;

import org.projlivraria.ctrl.HomeController;
import org.projlivraria.dao.ProdutoDAO;
import org.projlivraria.mdl.Produto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc//Habilita o componente MVC do framework
@ComponentScan(basePackageClasses={HomeController.class, ProdutoDAO.class, Produto.class})//Informa os pacotes que serão lidos p/ configurar por meio de classe base
public class ConfiguracaoAplicacao {
	@Bean//Informa que será uma instância/recurso gerenciado pelo framework
	public InternalResourceViewResolver viewResolver(){//Usado p/ configurar um tradutor/buscador de visualizações
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();//Implementação p/ resolvedor interno
		resolver.setPrefix("/WEB-INF/paginas/");//Prefixo de onde irá buscar as visualizações, caminho base
		resolver.setSuffix(".jsp");//Sufixo. A extensão de visualizações que irá buscar (Páginas jsp)
		return resolver;
	}
}

package org.projlivraria.cfg;

import org.projlivraria.ctrl.HomeController;
import org.projlivraria.dao.ProdutoDAO;
import org.projlivraria.mdl.Produto;
import org.projlivraria.util.SalvadorArquivo;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc//Habilita o componente MVC do framework
@ComponentScan(basePackageClasses={HomeController.class, ProdutoDAO.class, Produto.class,
		SalvadorArquivo.class})//Informa os pacotes que serão lidos p/ configurar por meio de classe base
public class ConfiguracaoAplicacao {
	@Bean//Informa que será uma instância/recurso gerenciado pelo framework
	public InternalResourceViewResolver viewResolver(){//Usado p/ configurar um tradutor/buscador de visualizações
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();//Implementação p/ resolvedor interno
		resolver.setPrefix("/WEB-INF/paginas/");//Prefixo de onde irá buscar as visualizações, caminho base
		resolver.setSuffix(".jsp");//Sufixo. A extensão de visualizações que irá buscar (Páginas jsp)
		resolver.setExposedContextBeanNames("carrinhoCompra");//Expõe para consumo via expressão pelo nome da classe
		return resolver;
	}
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/mensagens");
		messageSource.setCacheSeconds(3600);//Intervalo de tempo para recarregar arquivo c/ mensagens atualizadas
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	@Bean
	public FormattingConversionService mvcConversionService() {//Instância p/ conversão de formatos
		DefaultFormattingConversionService service = new DefaultFormattingConversionService();
		DateFormatterRegistrar dateFormatter = new DateFormatterRegistrar();
		dateFormatter.setFormatter(new DateFormatter("dd/MM/yyyy"));
		dateFormatter.registerFormatters(service);
		return service;
	}
	@Bean
	public MultipartResolver multipartResolver() {//Identifica para tratamento os arquivos enviados 
		return new StandardServletMultipartResolver();
	}
}

package org.projlivraria.cfg;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {
	//Necessário implementar interface de configuração p/ honrar os parâmetros necessários de inicialização 
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{ConfiguracaoAplicacao.class, ConfigJPA.class};//Classes de configuração que devem ser lidas
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};//Informa a partir de qual mapeamento o framework atenderá requisições
	}
	@Override
	protected Filter[] getServletFilters() {//Filtros que serão carregados
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");//Filtro p/ codificação de caracteres
		return new Filter[]{characterEncodingFilter};
	}
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));//Determina a forma padrão como os arquivos serão recebidos
	}
}

package org.projlivraria.cfg;

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

}

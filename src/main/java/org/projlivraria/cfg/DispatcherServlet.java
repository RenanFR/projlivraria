package org.projlivraria.cfg;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {
	//Necessário implementar interface de configuração p/ honrar os parâmetros necessários de inicialização 
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {ConfigSeguranca.class, ConfiguracaoAplicacao.class, ConfigJPA.class, FonteDadosHomologacao.class};
	}//Classes de configuração que devem ser lidas primeiro (Essenciais)

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{};//Classes de configuração que devem ser lidas
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
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addListener(RequestContextListener.class);
		servletContext.setInitParameter("spring.profiles.active", "producao");//Cria uma variável para configurar o profile a ser utilizado
	}
}

/**
 * @author renanfr
 *
 */
package org.projlivraria.cfg;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class FonteDadosHomologacao {
	@Bean
	@Profile("homologacao")//Configurando um Profile (Fonte de dados)
	public DataSource dataSource() {
		DriverManagerDataSource source = new DriverManagerDataSource();
		source.setUrl("jdbc:mysql://localhost:3306/projlivraria_homologacao");
		source.setDriverClassName("com.mysql.jdbc.Driver");
		source.setUsername("root");
		source.setPassword("Callable");
		return source;
	}
}

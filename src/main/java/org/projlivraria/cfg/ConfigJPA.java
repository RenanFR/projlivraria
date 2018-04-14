package org.projlivraria.cfg;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement//Habilita o controle de transações
public class ConfigJPA {
	//Permitirá a injeção do gerenciador de entidades por meio de sua fábrica
	@Bean
	public LocalContainerEntityManagerFactoryBean factory(){
		LocalContainerEntityManagerFactoryBean bean =//Será invocado quando o gerenciador de entidades for invocado
				new LocalContainerEntityManagerFactoryBean();
		Properties properties = new Properties();//Irá armazenar algumas propriedades/parâmetros p/ o funcionamento da JPA
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");//Identifica o dialeto de SQL a ser utilizado
		properties.setProperty("hibernate.show_sql", "true");//Denota se as operações SQL geradas pela JPA aparecerão no console
		properties.setProperty("hibernate.hbm2ddl.auto", "update");//Estratégia de mapeamento objeto-relacional
		bean.setJpaProperties(properties);//Adiciona as propriedades configuradas acima a Factory
		DriverManagerDataSource dataSource = new DriverManagerDataSource();//Irá armazenar os parâmetros de conexão com a base
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");//Irá buscar da biblioteca do provedor que deve estar nas dependências
		dataSource.setUrl("jdbc:mysql://localhost:3306/projlivraria");//Caminho da base de dados
		dataSource.setUsername("root"); dataSource.setPassword("root");//Usuário e senha p/ acesso
		bean.setDataSource(dataSource);//Adiciona a fábrica os parâmetros da fonte de dados configurados acima
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();//A implementação da especificação JPA que iremos usar
		bean.setJpaVendorAdapter(adapter);//Informa a implementação da especificação que será usada no gerenciador
		bean.setPackagesToScan("org.projlivraria.mdl");//Onde irá buscar os modelos p/ mapeamento objeto relacional
		return bean;
	}
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory factory){//Inicializa o recurso responsável por gerenciar transações
		JpaTransactionManager manager = new JpaTransactionManager(factory);//Associa o gerenciador de transações com nossa fábrica de gerenciador de entidades
		return manager;
	}
}

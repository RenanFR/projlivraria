/**
 * @author renanfr
 *
 */
package org.projlivraria.cfg;

import org.projlivraria.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebMvcSecurity
public class ConfigSeguranca extends WebSecurityConfigurerAdapter {//Devemos criar a classe para parametrizar a seguranca de nossa aplicação
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Override
	protected void configure(HttpSecurity http) throws Exception {//Nesse método restringimos ou liberamos endereços
		http.authorizeRequests()
			.antMatchers("/produtos/novo").hasRole("ADM")//Realizar as restrições e em seguida liberações
			.antMatchers("/produtos/cadastrar").hasRole("ADM")
			.antMatchers("/produtos/lista").hasAnyRole("USR", "ADM")
			.antMatchers("/produtos/detalhes").hasRole("USR")
			.antMatchers("/home").permitAll()
		.anyRequest().authenticated()//Usamos um Builder p/ configurar detalhes de autenticação/autorização
		.and().formLogin().and().httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Informa o repositório de usuário e codificador de senhas
		auth//Autenticação volátil em memória para desenvolvimento
			.inMemoryAuthentication().withUser("renanfr").password("renanfr").roles("ADM");
		auth.
			inMemoryAuthentication().withUser("andre").password("andre").roles("USR");
		auth.userDetailsService(usuarioDAO)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
}

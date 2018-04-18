/**
 * @author renanfr
 *
 */
package org.projlivraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.projlivraria.mdl.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAO implements UserDetailsService {//Repositório p/ controle de persistência de usuário/autenticação
	@PersistenceContext
	private EntityManager entityManager; 
	@Override
	public UserDetails loadUserByUsername(String idUsuario) throws UsernameNotFoundException {
		return entityManager.createQuery("SELECT u FROM Usuario u WHERE u.nomeUsuario = :usuarioBuscado", Usuario.class)
				.setParameter("usuarioBuscado", idUsuario)
				.getSingleResult();//Busca do modelo que representa os usuário de nossa aplicação por meio da abstração UserDetails
	}

}

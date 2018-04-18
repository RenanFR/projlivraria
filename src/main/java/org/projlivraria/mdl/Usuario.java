/**
 * @author renanfr
 *
 */
package org.projlivraria.mdl;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@Entity
public class Usuario implements UserDetails { //Os detalhes de usuário estarão nesse modelo
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String nomeUsuario;
	private String senhaUsuario;
	@OneToMany(fetch=FetchType.EAGER)
	private List<Perfil> listaPerfil;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return listaPerfil;
	}

	@Override
	public String getPassword() {
		return senhaUsuario;
	}

	@Override
	public String getUsername() {
		return nomeUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

/**
 * @author renanfr
 *
 */
package org.projlivraria.mdl;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
@Entity
public class Perfil implements GrantedAuthority {//Essa classe controla o tipo de controle, nesse caso faremos com perfil
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String descPerfil;
	@Override
	public String getAuthority() {
		return descPerfil;
	}

}

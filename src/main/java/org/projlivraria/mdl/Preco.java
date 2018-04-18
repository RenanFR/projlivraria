package org.projlivraria.mdl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable//Permite que seja mapeada como elemento de outra entidade
public class Preco implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal valor;
	private TipoPreco tipoPreco;
	public Preco(BigDecimal valor, TipoPreco tipoPreco) {
		this.valor = valor;
		this.tipoPreco = tipoPreco;
	}
	public Preco() {
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoPreco getTipoPreco() {
		return tipoPreco;
	}
	public void setTipoPreco(TipoPreco tipoPreco) {
		this.tipoPreco = tipoPreco;
	}
}

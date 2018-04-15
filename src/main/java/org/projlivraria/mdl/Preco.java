package org.projlivraria.mdl;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable//Permite que seja mapeada como elemento de outra entidade
public class Preco {
	private BigDecimal valor;
	private TipoPreco tipoPreco;
	
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
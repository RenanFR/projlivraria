package org.projlivraria.mdl;

import java.math.BigDecimal;

public class ItemCarrinho {
	private Produto produto;
	private TipoPreco tipoPreco;
	
	public ItemCarrinho(Produto produto, TipoPreco tipoPreco) {
		this.produto = produto;
		this.tipoPreco = tipoPreco;
	}

	@Override
	public String toString() {
		return "ItemCarrinho [produto=" + produto + ", tipoPreco=" + tipoPreco + "]";
	}
	
	public BigDecimal getTotal(int quantidade) {
		return this.getPreco().multiply(new BigDecimal(quantidade));
	}
	
	public BigDecimal getPreco(){
		return produto.precoPara(tipoPreco);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCarrinho other = (ItemCarrinho) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (tipoPreco != other.tipoPreco)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((tipoPreco == null) ? 0 : tipoPreco.hashCode());
		return result;
	}	
}

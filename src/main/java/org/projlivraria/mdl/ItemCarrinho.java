package org.projlivraria.mdl;

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
	
}

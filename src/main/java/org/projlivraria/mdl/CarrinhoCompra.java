package org.projlivraria.mdl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CarrinhoCompra {
	private Map<ItemCarrinho, Integer> itensCarrinho = new LinkedHashMap<>();
	

	public void adcCarrinho(ItemCarrinho itemCarrinho) {
		if(!itensCarrinho.containsKey(itemCarrinho)) {
			itensCarrinho.put(itemCarrinho, 1);
		} else {
			int qtdAnterior = itensCarrinho.get(itemCarrinho);
			itensCarrinho.put(itemCarrinho, ++qtdAnterior);
		}
	}
	public Map<ItemCarrinho, Integer> getItensCarrinho() {
		return itensCarrinho;
	}
	
	public void setItensCarrinho(Map<ItemCarrinho, Integer> itensCarrinho) {
		this.itensCarrinho = itensCarrinho;
	}
}

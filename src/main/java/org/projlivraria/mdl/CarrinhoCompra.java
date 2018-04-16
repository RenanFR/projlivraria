package org.projlivraria.mdl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)//A instância tem escopo sessão
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

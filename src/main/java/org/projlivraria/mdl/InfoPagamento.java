package org.projlivraria.mdl;

import java.math.BigDecimal;

public class InfoPagamento {

	private BigDecimal value;

	public InfoPagamento(BigDecimal value) {
		this.value = value;
	}
	public BigDecimal getValue() {
		return value;
	}
}

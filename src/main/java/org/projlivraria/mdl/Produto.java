package org.projlivraria.mdl;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity//Informa que o modelo será mapeado p/ base de dados
public class Produto {
	@Id//Informa o campo que será o identificador p/ essa tabela
	private String titulo;
	private String descricao;
	private int numeroPaginas;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	@Override
	public String toString() {
		return "Produto [titulo=" + titulo + ", descricao=" + descricao + ", numeroPaginas=" + numeroPaginas + "]";
	}
}

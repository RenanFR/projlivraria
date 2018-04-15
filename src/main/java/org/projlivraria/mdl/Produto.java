package org.projlivraria.mdl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity//Informa que o modelo será mapeado p/ base de dados
public class Produto {
	@Id//Informa o campo que será o identificador p/ essa tabela
	private String titulo;
	private String descricao;
	private int numeroPaginas;
	@DateTimeFormat
	private Calendar dataPublicacao;
	@ElementCollection//Cria tabela auxiliar para correspondências de um p/ cada valor do outro
	private List<Preco> precos;
	private String caminhoSumario;//Irá armazenar o caminho do arquivo
	
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
	public List<Preco> getPrecos() {
		return precos;
	}
	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}
	public Calendar getDataPublicacao() {
		return dataPublicacao;
	}
	public void setDataPublicacao(Calendar dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	public String getCaminhoSumario() {
		return caminhoSumario;
	}
	public void setCaminhoSumario(String caminhoSumario) {
		this.caminhoSumario = caminhoSumario;
	}
	@Override
	public String toString() {
		return "Produto [titulo=" + titulo + ", descricao=" + descricao + ", numeroPaginas=" + numeroPaginas + "]";
	}
}

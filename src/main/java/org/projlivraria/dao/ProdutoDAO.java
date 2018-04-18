package org.projlivraria.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.projlivraria.mdl.Produto;
import org.projlivraria.mdl.TipoPreco;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository//Informa ao Spring que é um componente passível de injeção do tipo repositório de dados
@Transactional//Define que as operações aqui utilizarão transação
public class ProdutoDAO {
	@PersistenceContext//Anotação especial para injeção do gerenciador de entidades
	private EntityManager entityManager;//Gerenciador de entidades JPA p/ manipulação de registros
	@CacheEvict(value="livros", allEntries=true)//Irá limpar o cache quando for invocado p/ atualizar dados disponíveis
	public void salvar(Produto produto) {
		entityManager.persist(produto);//Método p/ persistência na base
	}
	@Cacheable("livros")//Habilita o cache p/ o método
	public List<Produto> todos() {
		return entityManager.createQuery("SELECT DISTINCT(p) FROM Produto p JOIN FETCH p.precos prc", Produto.class)
				.getResultList();
	}
	
	public Produto livroPorNome(String nomeLivro) {
		return entityManager
				.createQuery("SELECT DISTINCT(p) FROM Produto p JOIN FETCH p.precos WHERE p.titulo =:nomeLivroParam", Produto.class)
					//Realiza o Fetch na consulta ao invés de indicar na entidade que deve ser Eager (Mais correto)
				.setParameter("nomeLivroParam", nomeLivro)//Realiza o binding do parâmetro na consulta com o recebido no método
				.getSingleResult();
	}
	public Produto buscaPorId(String livroId) {
		return entityManager.find(Produto.class, livroId);
	}
	
	public Produto buscaPorTitulo(String livroId) {
		return entityManager.createQuery("SELECT p FROM Produto p JOIN FETCH p.precos prc "
				+ "WHERE p.titulo LIKE :tituloLivro", Produto.class)
				.setParameter("tituloLivro", livroId).getSingleResult();
	}
	
	public BigDecimal precosPorTipoLivro(TipoPreco tipoPreco) {
		return entityManager.createQuery("SELECT SUM(prc.valor) FROM Produto prod "
				+ "JOIN prod.precos prc WHERE prc.tipoPreco =:tipo", BigDecimal.class)
				.setParameter("tipo", tipoPreco).getSingleResult();
	}
}

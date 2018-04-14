package org.projlivraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.projlivraria.mdl.Produto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository//Informa ao Spring que é um componente passível de injeção do tipo repositório de dados
@Transactional//Define que as operações aqui utilizarão transação
public class ProdutoDAO {
	@PersistenceContext//Anotação especial para injeção do gerenciador de entidades
	private EntityManager entityManager;//Gerenciador de entidades JPA p/ manipulação de registros
	public void salvar(Produto produto) {
		entityManager.persist(produto);//Método p/ persistência na base
	}
}

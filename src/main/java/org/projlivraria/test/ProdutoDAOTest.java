/**
 * @author renanfr
 *
 */
package org.projlivraria.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.projlivraria.cfg.ConfigJPA;
import org.projlivraria.cfg.FonteDadosHomologacao;
import org.projlivraria.dao.ProdutoDAO;
import org.projlivraria.mdl.Preco;
import org.projlivraria.mdl.Produto;
import org.projlivraria.mdl.TipoPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)//Irá possibilitar execução dos testes integrado com o contexto do framework
@ContextConfiguration(classes= {ConfigJPA.class, ProdutoDAO.class, FonteDadosHomologacao.class})
//As classes de configuração que devem ser lidas p/ o teste
@ActiveProfiles("homologacao")//Para que utilize a fonte de dados de homologação
public class ProdutoDAOTest {
	
	@Autowired//Será injetado pelo framework pois carregamos nas classes de configuração
	private ProdutoDAO produtoDAO;
	
	@Test//É um método de teste
	@Transactional//Irá gerenciar as transações iniciadas dentro do método de teste
	public void somaPrecoPorTipoLivro() {
		Arrays.asList(new Produto("Livro teste", "Esse livro é para testar", 150, Calendar.getInstance(),
				Arrays.asList(new Preco(BigDecimal.TEN, TipoPreco.COMBO)), "")).stream()
		.forEach(prod -> produtoDAO.salvar(prod));
		Assert.assertEquals(new BigDecimal(10).setScale(2), produtoDAO.precosPorTipoLivro(TipoPreco.COMBO));
	}
}

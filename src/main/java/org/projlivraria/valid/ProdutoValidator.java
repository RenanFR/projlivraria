package org.projlivraria.valid;

import org.projlivraria.mdl.Produto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProdutoValidator implements Validator {//O contrato da especificação de validação contem suporte e validação em si
	@Override
	public boolean supports(Class<?> objSuportado) {//Informa qual entidade será suportada por esse validador
		return Produto.class.isAssignableFrom(objSuportado);
	}
	@Override
	public void validate(Object objetoValidado, Errors erros) {
		Produto produto = (Produto) objetoValidado;//Necessário realizar conversão p/ validar entidade
		ValidationUtils.rejectIfEmpty(erros, "titulo", "field.required");//Classe com validações comuns
		if(produto.getNumeroPaginas() < 30) {//Condicionais p/ implementar validações do domínio/regras de negócio
			erros.rejectValue("numeroPaginas", "field.required");//Adicionando a lista de erros
		}
	}
}

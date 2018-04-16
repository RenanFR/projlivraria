<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros</title>
</head>
<body>
	<h1>Livros inseridos</h1>
	<h3>${mensagem}</h3>
	<table>
		<thead>
			<tr>
				<th>T�tulo</th>
				<th>Descri��o</th>
				<th>N�mero de p�ginas</th>
				<th>Publica��o do livro</th>
				<th>Pre�o E-book</th>
				<th>Pre�o Impresso</th>
				<th>Pre�o Combo</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todosLivros}" var="l">
				<tr>
					<td>
						<a href="${s:mvcUrl('PC#detalhesLivro').arg(0, l.titulo).build()}">${l.titulo}</a>
					</td>
					<td>${l.descricao }</td>
					<td>${l.numeroPaginas }</td>
					<td><fmt:formatDate value="${l.dataPublicacao.time}" pattern="dd/MM/yyyy"/></td>
						<c:forEach items="${l.precos}" var="p">
							<form:form servletRelativeAction="${s:mvcUrl('CC#adicionar').arg(0, l.titulo).arg(1, p.tipoPreco).build()}">
								<input type="text" name="tipoLivro" value="${p.tipoPreco}"	/>
								<input type="hidden" name="livroId" value="${l.titulo}"	/>
	<%-- 							<td>Pre�o ${p.tipoPreco} do livro: ${p.valor} </td> --%>
								<input type="submit" value="Adicionar ao carrinho"	/>
							</form:form>
						</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
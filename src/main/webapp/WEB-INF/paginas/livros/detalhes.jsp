<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livro ${livroEncontrado.titulo}</title>
</head>
<body>
	<table>
		<tr>
			<td>T�tulo do livro:	${livroEncontrado.titulo}</td>
		</tr>
		<tr>
			<td>Descri��o do livro:	${livroEncontrado.descricao}</td>
		</tr>
		<tr>
			<td>N�mero de p�ginas do livro:	${livroEncontrado.numeroPaginas}</td>
		</tr>
		<tr>
			<td>Publica��o do livro:	<fmt:formatDate value="${livroEncontrado.dataPublicacao.time}" pattern="dd/MM/yyyy"/>	</td>
		</tr>
		<tr>
			<td>
				<c:forEach items="${livroEncontrado.precos}" var="p">
					Pre�o ${p.tipoPreco} do livro: ${p.valor} <br>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>Sum�rio do livro:	${livroEncontrado.caminhoSumario}</td>
		</tr>
	</table>
</body>
</html>
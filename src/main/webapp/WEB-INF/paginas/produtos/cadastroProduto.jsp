<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar novo livro</title>
</head>
<body>
	<form action="/produtos/cadastrar">
		<div>
			<label>Título</label>
			<input type="text" name="titulo">
		</div>
		<div>
			<label>Descrição</label>
			<textarea rows="5" cols="2" name="descricao"></textarea>
		</div>
		<div>
			<label>Número de páginas</label>
			<input type="number" name="numeroPaginas">
		</div>
	</form>
</body>
</html>
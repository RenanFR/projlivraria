<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastrar novo livro</title>
</head>
<body>
	<form:form action="${s:mvcUrl('PC#cadastrar').build()}" method="POST" commandName="produto"
	enctype="multipart/form-data">
		<div>
			<label>Título</label>
			<form:input path="titulo"	/>
			<form:errors path="titulo"></form:errors>
		</div>
		<div>
			<label>Descrição</label>
			<form:textarea rows="5" cols="5" path="descricao"	/>
			<form:errors path="descricao"></form:errors>
		</div>
		<div>
			<label>Número de páginas</label>
			<form:input path="numeroPaginas"	/>
			<form:errors path="numeroPaginas"></form:errors>
		</div>
		<div>
			<label>Data de publicação</label>
			<form:input path="dataPublicacao"	/>
			<form:errors path="dataPublicacao"></form:errors>
		</div>
		<div>
			<label>Sumário</label>
			<input type="file" name="sumario"	/>
			<form:errors path="caminhoSumario"></form:errors>
		</div>
		<c:forEach items="${tiposPreco}" var="tp" varStatus="st">
			<div>
				<label>${tp}</label>
				<form:input path="precos[${st.index}].valor"	/>
				<form:input path="precos[${st.index}].tipoPreco" value="${tp}"	/>
			</div>
		</c:forEach>
		<button type="submit">Cadastrar</button>
	</form:form>
</body>
</html>
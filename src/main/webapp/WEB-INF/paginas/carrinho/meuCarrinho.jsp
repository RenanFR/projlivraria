<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>meuCarrinho</title>
<link rel="stylesheet" href='<c:url value="resources/estilo.css"></c:url>'>
</head>
<body>
	<p id="carrinhoCompra">
		${carrinhoCompra.itensCarrinho}
	</p>
	<button>
		<a href="${s:mvcUrl('PC#listaLivros').build()}">Volte para lista</a>
	</button>
	<button>
		<a href="${s:mvcUrl('PCC#finalizarCompra').build()}">Finalizar compra</a>
	</button>
</body>
</html>
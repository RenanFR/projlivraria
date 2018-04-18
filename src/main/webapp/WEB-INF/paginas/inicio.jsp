<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ol� Mundo</title>
</head>
<body>
	<h1>Meu nome � Renan</h1>
	<h2>
		<s:message code="menu"></s:message>
	</h2>
	<a href="?locale=pt_BR">
		<button>Idioma português</button>
	</a>
	<a href="?locale=en_US">
		<button>Idioma inglês</button>
	</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenida</title>
</head>
<body>
	<u>Bienvenido:</u>
	<ol>
	${persona.nombre} ${persona.apellido}
	</ol>
	<br>
	<b>Test lista-personas</b>
	<br>
	<ol>
		<c:forEach var="actual" items="${listaPersonas}">
			<li>Nombre: ${actual.nombre}</li>
		</c:forEach>
	</ol>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Lista</title>
	</head>
	<body>
		<ul>
<c:forEach items="${listaArray}" var="lista">
			<li>${lista}</li>
</c:forEach>
		</ul>
	</body>
</html>
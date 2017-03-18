<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Prepara tu sanguche</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="css/bootstrap-theme.min.css" rel="stylesheet">

</head>
<body role="document">
	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="page-header">
			<h3>
				Bienvenido a Sanguchetto ${username} ! <br> <br> <small>Elija
					sus condimentos e ingredientes preferidos y pruebe el sandwitch
					perfecto para usted. </small>
			</h3>
		</div>
		<div class="row">
			<div class="col-md-6">
				<form:form action="prepara-tu-sanguche" method="POST"
					modelAttribute="ingredientesUsados">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">Ingredientes</h3>
						</div>
						<div style="overflow-y: scroll; height: 200px">
							<table class="table table-striped">
								<tbody>
									<c:forEach var="ingrediente" items="${ingredientes}">
										<tr>
											<td>${ingrediente.getNombre()}</td>
											<td>$&nbsp;${ingrediente.getPrecio()}</td>
											<td><input type="checkbox" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<button type="submit" class="btn btn btn-success" value="Submit">Añadir
						Ingredientes</button>
					<button type="button" class="btn btn btn-danger">Quitar
						condimentos</button>
				</form:form>
			</div>
			<div class="col-md-6">
				<form:form action="prepara-tu-sanguche" method="POST"
					modelAttribute="ingredientesUsados">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">Condimentos</h3>
						</div>
						<div style="overflow-y: scroll; height: 200px">
							<table class="table table-striped">
								<tbody>
									<c:forEach var="ingrediente" items="${condimentos}">
										<tr>
											<td>${ingrediente.getNombre()}</td>
											<td>$&nbsp;${ingrediente.getPrecio()}</td>
											<td><input type="checkbox" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<button type="submit" class="btn btn btn-success" value="Submit">Añadir
						Condimentos</button>
					<button type="button" class="btn btn btn-danger">Quitar
						condimentos</button>
				</form:form>
			</div>
		</div>
		<br> <br>
		<div class="col-md-6">//HACER Lista de seleccionados</div>
		<div class="col-md-6">//Hacer precio, y descuento</div>
	</div>
</body>
</html>
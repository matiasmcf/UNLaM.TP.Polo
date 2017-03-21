<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestione el stock</title>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
	<div class="container theme-showcase" role="main">
		<div class="page-header">
			<h3>
				Bienvenido ${username} ! <br> <br> <small>Aqui
					podra gestionar el stock, adquirir nuevos productos, eliminar
					antiguos, aumentar stock de existentes, entre otras cosas. </small>
			</h3>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Ingredientes</h3>
					</div>
					<div style="overflow-y: scroll; height: 200px">
						<table class="table table-striped">
							<tbody>
								<c:forEach var="ingrediente" items="${ingredientes}">
									<tr>
										<td>${ingrediente.nombre}</td>
										<td>$&nbsp;${ingrediente.precio}</td>
										<td>${stock.obtenerStock().get(ingrediente)}</td>
										<td><input /></td>
										<td><button type="submit" class="btn btn btn-success"
												value="Submit">Comprar stock</button></td>
										<td><button type="submit" class="btn btn btn-danger"
												value="Submit">Eliminar producto</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<button type="submit" class="btn btn btn-success" value="Submit">Añadir
					Ingrediente</button>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<br>
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
										<td><input /></td>
										<td><button type="submit" class="btn btn btn-success"
												value="Submit">Comprar stock</button></td>
										<td><button type="submit" class="btn btn btn-danger"
												value="Submit">Eliminar producto</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<button type="submit" class="btn btn btn-success" value="Submit">Añadir
					Condimento</button>
			</div>
		</div>
	</div>
</body>
</html>
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
	<div class="container theme-showcase">
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
										<td align="RIGHT">Stock:
											${stock.obtenerStock().get(ingrediente)}</td>
										<form:form action="gestion-sitio/comprar"
											modelAttribute="objetoCompra" method="POST">
											<td align="RIGHT">Cantidad:</td>
											<td><form:input type="hidden" path="nombre"
													value="${ingrediente.nombre}" /></td>
											<td align="LEFT"><form:input style="width: 40px;"
													path="cantidad" /></td>
											<td align="LEFT"><button type="submit"
													class="btn btn btn-success" value="Submit">Comprar
													stock</button></td>
										</form:form>
										<form:form action="gestion-sitio/eliminar-ingrediente"
											modelAttribute="ingredienteEliminar" method="POST">
											<td><form:input type="hidden" path="nombre"
													value="${ingrediente.nombre}" /></td>
											<td align="RIGHT"><button type="submit"
													class="btn btn btn-danger" value="Submit">Eliminar
													producto</button></td>
										</form:form>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<form:form action="ingrediente-nuevo">
					<button type="submit" class="btn btn btn-success" value="Submit">Añadir
						Ingrediente</button>
				</form:form>
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
										<td align="RIGHT">Stock:
											${stock.obtenerStock().get(ingrediente)}</td>
										<form:form action="gestion-sitio/comprar"
											modelAttribute="objetoCompra" method="POST">
											<td align="RIGHT">Cantidad:</td>
											<td><form:input type="hidden" path="nombre"
													value="${ingrediente.nombre}" /></td>
											<td align="LEFT"><form:input style="width: 40px;"
													path="cantidad" /></td>
											<td align="LEFT"><button type="submit"
													class="btn btn btn-success" value="Submit">Comprar
													stock</button></td>
										</form:form>
										<form:form action="gestion-sitio/eliminar-ingrediente"
											modelAttribute="ingredienteEliminar" method="POST">
											<td><form:input type="hidden" path="nombre"
													value="${ingrediente.nombre}" /></td>
											<td align="RIGHT"><button type="submit"
													class="btn btn btn-danger" value="Submit">Eliminar
													producto</button></td>
										</form:form>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<form:form action="condimento-nuevo">
					<button type="submit" class="btn btn btn-success" value="Submit">Añadir
						Condimento</button>
				</form:form>
				<br>
			</div>
		</div>
	</div>
</body>
</html>
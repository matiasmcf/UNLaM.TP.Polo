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
<body>
	<div class="container theme-showcase">
		<div class="page-header">
			<h3>
				Bienvenido a Sanguchetto ${username} ! <br> <br> <small>Elija
					sus condimentos e ingredientes preferidos y pruebe el sandwitch
					perfecto para usted. </small>
			</h3>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Ingredientes</h3>
					</div>
					<div style="overflow-y: scroll; height: 200px">
						<table class="table table-striped">
							<tbody>
								<c:forEach var="ingrediente" items="${ingredientes}">
									<form:form modelAttribute="agregarIng" method="POST">
										<tr>
											<td>${ingrediente.getNombre()}</td>
											<td>$&nbsp;${ingrediente.getPrecio()}</td>
											<td><form:input type="hidden" path="nombre"
													value="${ingrediente.nombre}" readonly="true" /></td>
											<td><form:input type="hidden" path="tipo"
													value="${ingrediente.tipo}" readonly="true" /></td>
											<td><form:input type="hidden" path="precio"
													value="${ingrediente.precio}" readonly="true" /></td>
											<td>
												<button type="submit" class="btn btn btn-success">Añadir
												</button>
											</td>
										</tr>
									</form:form>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Condimentos</h3>
					</div>
					<div style="overflow-y: scroll; height: 200px">
						<table class="table table-striped">
							<tbody>
								<c:forEach var="condimento" items="${condimentos}">
									<form:form modelAttribute="agregarIng" method="POST">
										<tr>
											<td>${condimento.getNombre()}</td>
											<td>$&nbsp;${condimento.getPrecio()}</td>
											<td><form:input type="hidden" path="nombre"
													value="${condimento.nombre}" readonly="true" /></td>
											<td><form:input type="hidden" path="tipo"
													value="${condimento.tipo}" readonly="true" /></td>
											<td><form:input type="hidden" path="precio"
													value="${condimento.precio}" readonly="true" /></td>
											<td>
											<td>
												<button type="submit" class="btn btn btn-success"
													value="Submit">Añadir</button>
											</td>
										</tr>
									</form:form>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Ticket</h3>
					</div>
					<div style="overflow-y: scroll; height: 200px">
						<table class="table table-striped">
							<tbody>
								<tr>
									<td><h4>Ingredientes:</h4></td>
								</tr>
								<c:forEach var="ingrediente"
									items="${sanguche.verIngredientes()}">
									<form:form modelAttribute="quitarIng" method="POST">
										<tr>
											<td>${ingrediente.getNombre()}</td>
											<td>$&nbsp;${ingrediente.getPrecio()}</td>
											<td><form:input type="hidden" path="nombre"
													value="${ingrediente.nombre}" /></td>
											<td><form:input type="hidden" path="tipo"
													value="${ingrediente.tipo}" /></td>
											<td><form:input type="hidden" path="precio"
													value="${ingrediente.precio}" /></td>
											<td>
												<button type="submit" class="btn btn btn-danger">Quitar
												</button>
											</td>
										</tr>
									</form:form>
								</c:forEach>
								<tr>
									<td><h4>Condimentos:</h4></td>
								</tr>
								<c:forEach var="condimento" items="${sanguche.verCondimentos()}">
									<form:form modelAttribute="quitarIng" method="POST">
										<tr>
											<td>${condimento.getNombre()}</td>
											<td>$&nbsp;${condimento.getPrecio()}</td>
											<td><form:input type="hidden" path="nombre"
													value="${condimento.nombre}" /></td>
											<td><form:input type="hidden" path="tipo"
													value="${condimento.tipo}" /></td>
											<td><form:input type="hidden" path="precio"
													value="${condimento.precio}" /></td>
											<td>
												<button type="submit" class="btn btn btn-danger">Quitar
												</button>
											</td>
										</tr>
									</form:form>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-6"> Total: ${sanguche.getPrecio()}
			</div>
		</div>
	</div>
</body>
</html>
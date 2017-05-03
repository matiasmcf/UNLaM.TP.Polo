<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Nuevo Condimento</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="css/bootstrap-theme.min.css" rel="stylesheet">

</head>

<body role="document">
	<div class="container theme-showcase" role="main">
		<br>
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<div align="center" class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Ingrese datos del condimento</h3>
					</div>
					<div class="panel-body" align="center">
						<form:form type="post" action="condimento-nuevo/datos"
							modelAttribute="condimento" method="POST">
							<br>
							<label>Nombre:&nbsp;</label>
							<form:input path="nombre" type="text"
								maxlength="20" minlength="3" placeholder="Ingrese nombre"  required="true"  />
							<br>
							<br>
							<label>Precio:&nbsp&nbsp&nbsp;</label>
							<form:input type="number" min="0.01" step="0.01" path="precio"
								placeholder="Ingrese precio" required="true" />
							<br>
							<br>
							<table>
								<tbody>
									<tr>
										<td><a href="/gestion-sitio" role="button"
											class="btn btn btn-primary">Cancelar</a>&nbsp;&nbsp;</td>
										<td><button type="submit" class="btn btn btn-success" value="Submit">Agregar&nbsp</button></button></td>

									</tr>
								</tbody>
							</table>
						</form:form>
					</div>
				</div>
			</div>
			<div class="col-sm-4"></div>
		</div>
	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
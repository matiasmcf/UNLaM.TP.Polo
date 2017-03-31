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

<title>Registro de usuario</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="css/bootstrap-theme.min.css" rel="stylesheet">

</head>

<body role="document">
	<div class="container theme-showcase" role="main">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<br> <br>
				<div align="center" class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Ingrese sus datos</h3>
					</div>
					<div class="panel-body">
						<form:form type="post" action="confirmarRegistro"
							modelAttribute="usuario">
							<br>
							<br>
							<label>Usuario:&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<form:input path="username" placeholder="Ingrese su usuario."
								type="text" required="true" maxlength="15" minlength="4" />
							<br>
							<h6>
								<p style="color: blue">
									<form:errors path="username" cssClass="error" />
								</p>
							</h6>
							<br>
							<label>Password:&nbsp;</label>
							<form:password path="password" placeholder="Ingrese su clave."
								required="true" maxlength="15" minlength="4" />
							<h6>
								<p style="color: blue">
									<form:errors path="password" cssClass="error" />
								</p>
							</h6>
							<h5>
								<p style="color: blue">
									<form:errors path="*" cssClass="error" />
								</p>
							</h5>
							<br>
							<table>
								<tbody>
									<tr>
										<td><a href="/sitio-polo" role="button"
											class="btn btn btn-primary">Cancelar </a>&nbsp;&nbsp;</td>
										<td><button type="submit" class="btn btn btn-success"
												value="Submit">Registrar</button></td>

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
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>

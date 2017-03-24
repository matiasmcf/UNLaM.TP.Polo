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

<title>Ingreso de datos</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="css/bootstrap-theme.min.css" rel="stylesheet">

</head>

<body role="document">
	<div class="container theme-showcase" role="main" align="CENTER">
		<br> <br> <br> <br> <br> <br> <br>
		<h2>
			<span class="label label-success">${userName} !, Gracias por
				comprar en Sanguchetto</span>
		</h2>
		<br> <br> <br> <br>
		<table>
			<tbody>
				<tr>
					<td><form:form action="/sitio-polo/u/${userName}/prepara-tu-sanguche">
							<button type="submit" class="btn btn btn-success" value="Submit">Comprar
								otro producto</button>
						</form:form></td>
					<td><form:form action="/sitio-polo">
							&nbsp;&nbsp;<button type="submit" class="btn btn btn-danger" value="Submit">Salir</button>
						</form:form></td>
				</tr>
			</tbody>
		</table>
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

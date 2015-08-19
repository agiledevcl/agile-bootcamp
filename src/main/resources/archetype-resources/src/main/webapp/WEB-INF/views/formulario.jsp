<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="robots" content="noindex">
	<title>formulario.jsp</title>	
	<!-- Stylesheets -->
	<link href="<c:url value="/resources/css/normalize.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/estilo.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/awesome-bootstrap-checkbox.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<link href="<c:url value="/resources/css/select.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/selectize.default.css" />" rel="stylesheet">

	<!-- SCRIPTS -->
	<script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />" ></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script src="<c:url value="/resources/js/angular.min.js" />" ></script>
	<script src="<c:url value="/resources/js/angular-route.min.js" />" ></script>
	<script src="<c:url value="/resources/js/angular-sanitize.min.js" />" ></script>
	<script src="<c:url value="/resources/js/ui-bootstrap-tpls-0.12.1.min.js" />" ></script>
</head>
<body>	
	


	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<h1>${titulo}</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<form method="post" action="getFormulario">
					<div class="form-group">
						<label for="commonName">Nombre completo</label>
						<input type="text" class="form-control" id="commonName" name="commonName" placeholder="Ingrese su nombre completo">
					</div>
					<div class="form-group">
						<label for="dni">Rut</label>
						<input type="text" class="form-control" id="dni" name="dni" placeholder="Ej. 11.111.111-1">
					</div>
					<div class="form-group">
						<label for="edad">Edad</label>
						<input type="number" class="form-control" id="edad" name="edad" placeholder="Ingrese su edad">
					</div>
					<div class="form-group">
						<label for="pais">Pais</label>
						<input type="test" class="form-control" id="pais" name="pais" placeholder="Ingrese su pais">
					</div>
					<div class="form-group">
						<label for="ciudad">Ciudad</label>
						<input type="text" class="form-control" id="ciudad" name="ciudad" placeholder="Ingrese su ciudad">
					</div>
					
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Enviar</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	
</body>
</html>

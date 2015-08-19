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
	<title>Home</title>	
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
			<div class="col-sm-10">
				<h5>Bienvenido(a) ${commonName}</h5>
			</div>
			<div class="col-xs-2">
				<a href="../logout" class="btn btn-danger pull-right" role="button">Salir</a>
			</div>
		</div>
	</div>
	
</body>
</html>

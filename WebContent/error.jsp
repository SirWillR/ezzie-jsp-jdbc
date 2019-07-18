<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="pt-br">
<head>
<title>Ezzie - Página Inicial</title>
<jsp:include page="includes/head.jsp" />
</head>
<body class="profile-page sidebar-collapse bg-ezzie">

	<div class="loader"></div>
	
	<jsp:include page="includes/navbar.jsp" />
	<jsp:include page="includes/login.jsp" />

	<div class="page-header" data-parallax="true">
		<div class="container">
			<div class="row">
				<div class="col-md-8 ml-auto mr-auto">
					<div class="brand text-center">
						<img src="resources/img/Ezzie-logo.png" class="img-fluid" alt="Ezzie" width="150px">
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="main main-raised">
		<div class="container">
			<div class="section" style="padding-top: 15px">
				<h2>
					<small class="text-muted">ERROR</small>
				</h2>
				<h3 style="text-align: center; color: blue">${msg}</h3>
				<h5 style="text-align: center; color: red">${error}</h5>
			</div>
		</div>
	</div>

	<jsp:include page="includes/footer.jsp" />
	<jsp:include page="includes/scriptsFooter.jsp" />
</body>
</html>
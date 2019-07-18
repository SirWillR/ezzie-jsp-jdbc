<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
						<img src="resources/img/Ezzie-logo.png" class="img-fluid" alt="Ezzie"
							width="150px">
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="main main-raised" style="margin-top: 0">
		<div class="profile-content">
			<div class="container">
				<div class="row">
					<div class="col-md-6 ml-auto mr-auto">
						<div class="profile">
							<div class="avatar">
							<c:choose>
							  	<c:when test="${user.fotoBase64Mini.isEmpty() == false}">
									<img src="${user.fotoBase64Mini}" alt="Circle Image" class="img-raised rounded-circle img-fluid">
							  	</c:when>
							  	<c:otherwise>
							   		<img src="resources/img/user.jpg" alt="Circle Image" class="img-raised rounded-circle img-fluid">
							  	</c:otherwise>
							</c:choose>
							</div>
							<div class="name">
								<h3 class="title">${user.nome}</h3>
							</div>
						</div>
					</div>
				</div>
				<div class="row justify-content-center ">
					<div class="col-md-10 ml-auto mr-auto">
						<div class="table-responsive">
							<h2>
								<small class="text-muted">Itens Achados Cadastrados:</small>
							</h2>
							<table class="table table-bordered text-center">
								<thead>
									<tr>
										<th scope="col">Título</th>
										<th scope="col">Descrição</th>
										<th scope="col">Tipo</th>
										<th scope="col">Excluir</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${objetos}" var="objeto">
										<tr>
											<c:if test="${objeto.situacao == 'Achado'}">
												<td>${objeto.titulo}</td>
												<td>${objeto.descricao}</td>
												<td>${objeto.tipo}</td>
												<td><a
													href="#"
													id="excluir" class="btn btn-danger btn-sm"><i
														class="fas fa-trash-alt"></i></a></td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<h2>
								<small class="text-muted">Itens Perdidos Cadastrados:</small>
							</h2>
							<table class="table table-bordered text-center">
								<thead>
									<tr>
										<th scope="col">Título</th>
										<th scope="col">Descrição</th>
										<th scope="col">Tipo</th>
										<th scope="col">Excluir</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${objetos}" var="objeto">
										<tr>
											<c:if test="${objeto.situacao == 'Perdido'}">
												<td>${objeto.titulo}</td>
												<td>${objeto.descricao}</td>
												<td>${objeto.tipo}</td>
												<td><a
													href="salvarTelefone?acao=delete&id=${objeto.id}"
													id="excluir" class="btn btn-danger btn-sm"><i
														class="fas fa-trash-alt"></i></a></td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="includes/footer.jsp" />
	<jsp:include page="includes/scriptsFooter.jsp" />
	<script>
    	$(function () {
	    	$("a#excluir").click(function(e) {
	    	    e.preventDefault();
	    	    var location = $(this).attr('href');
	    	    bootbox.confirm("Confirmar a exclusão?", function(confirmed) {
	    	        if(confirmed) {
	    	        	window.location.replace(location);
	    	        }
	    	    });
	    	});     
    	});
    </script>
</body>
</html>
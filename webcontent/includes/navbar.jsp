
<%@page import="model.Usuario"%>
<nav class="navbar fixed-top navbar-expand-lg bg-dark"
	role="navigation-demo">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-translate">
			<a class="navbar-brand" href="index.jsp">
				<div class="logo-image">
					<img src="resources/img/Ezzie.png" class="img-fluid" width="75px">
				</div>
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="sr-only">Toggle navigation</span> <span
					class="navbar-toggler-icon"></span> <span
					class="navbar-toggler-icon"></span> <span
					class="navbar-toggler-icon"></span>
			</button>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a href="achados.jsp" class="nav-link"> Achados
						<div class="ripple-container"></div>
				</a></li>
				<li class="nav-item"><a href="perdidos.jsp" class="nav-link"> Perdidos
						<div class="ripple-container"></div>
				</a></li>
				<%
					if (request.getSession().getAttribute("userSession") != null) {
						Usuario usuario = (Usuario) request.getSession().getAttribute("userSession");
				%>
				<li class="dropdown nav-item"><a href="#pablo"
					class="profile-photo dropdown-toggle nav-link"
					data-toggle="dropdown" aria-expanded="false">
						<div class="profile-photo-small">
							<img src='<%= usuario.getFotoBase64Mini() != null ? usuario.getFotoBase64Mini() : "resources/img/user.jpg" %>'
								alt="Circle Image" class="rounded-circle img-fluid">
						</div>
						<div class="ripple-container"></div>
				</a>
					<div class="dropdown-menu dropdown-menu-right">
						<h6 class="dropdown-header">Selecione uma opção</h6>
						<a href="PerfilServlet" class="dropdown-item">Meu Perfil</a> <a href="cadastrarObjeto.jsp"
							class="dropdown-item">Cadastrar Objeto</a> <a
							href="LoginServlet?acao=sair" class="dropdown-item">Sair</a>
					</div></li>
				<%
					} else {
				%>
				<button class="btn btn-round" data-toggle="modal"
					data-target="#loginModal"
					onclick="$('#msg').css('color', '#999'); $('#msg').html('Entrar no Ezzie');">Entrar</button>
				<%
					}
				%>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-->
</nav>
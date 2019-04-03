<%@page language="java" contentType="text/html" pageEncoding="UTF-8" import="java.util.List, model.*, model.dao.*"%>
<html>
    <head>
        <title>ProgWeb</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/style.css">
		<script type="text/javascript" src="js/script.js"></script>
    </head>
    <body>
    	<!--
		<div class="outer">
			<div class="middle">
				<div class="inner">
					<div>
						<div class="loader-bg"></div>
						<div class="loader"></div>
					</div>
				</div>
			</div>
		</div>
		-->
		<div id="mySidenav" class="sidenav">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<a href="index.jsp">Início</a>
			<a href="cadastrar.jsp">Cadastrar</a>
			<a href="listar.jsp">Perdidos</a>
		</div>

		<div class="menu">
			<span onclick="openNav()"><img src="img/icone-menu.png"></span>
		</div>
        <div class="container">  
			<form action="control/insereItemPerdido.jsp" class="form" method="post" tabindex="1">  
			   	<input type="text" class="form-input" name="nome" placeholder="Nome do Item" autocomplete="off" required />
				<select name="tipo" class="form-input" required>
					<option value="">Selecione o Tipo</option>
				 	<%
				  		TipoDeItemDAO tipoDoItemDao = DaoFactory.createTipoDeItemDAO();
				  		List<TipoDeItem> listTipos = tipoDoItemDao.findAll();

				  		for(TipoDeItem tipo : listTipos){
				  			%>
				  				<option value="<%= tipo.getIdTipo()%>"><%= tipo.getNome()%></option>
				  			<%
				  		}
				  	%>
				</select>
				<select name="cidade" class="form-input" required>
					<option value="">Selecione a Cidade</option>
				 	<%
				  		LocalizacaoDAO localizacaoDao = DaoFactory.createLocalizacaoDAO();
				  		List<Cidade> listCidades = localizacaoDao.findAllCity(11);

				  		for(Cidade cidade : listCidades){
				  			%>
				  				<option value="<%= cidade.getId()%>"><%= cidade.getNome()%></option>
				  			<%
				  		}
				  	%>
				</select>
				<input type="date" class="form-input" name="date" placeholder="Data" required />  
			  	<input type="text" class="form-input" name="local" placeholder="Local Encontrado" autocomplete="off" required />  
			 	<select name="prazo" class="form-input" required>
			 		<option value="">Selecione o Prazo</option>
			 		<option value="10">10</option>
			 		<option value="10">15</option>
			 		<option value="10">30</option>
			 		<option value="10">60</option>
			 		<option value="10">90</option>
			 	</select>
				<button type="submit" class="form-button">Enviar</button>  
			</form>  
		</div>  
    </body>
</html>
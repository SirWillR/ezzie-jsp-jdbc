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
			 <table>
			  <tr>
			    <th>Nome do Item</th>
			    <th>Tipo</th>
			    <th>Local</th>
			    <th>Data</th>
			    <th>Encontrado em</th>
			    <th>Prazo</th>
			    <th></th>
			  </tr>
			  	<%
			  		ItemPerdidoDAO itemPerdidoDao = DaoFactory.createItemPerdidoDAO();
			  		List<ItemPerdido> list = itemPerdidoDao.findAll();
			  		
			  		for(ItemPerdido item : list){
			  			%>
			  <tr>
			    <td><%= item.getNomeItem()%></td>
			    <td><%= item.getTipo().getNome()%></td>
			    <td><%= item.getLocalizacao().getCidade().getNome()%></td>
			    <td><%= item.getData()%></td>
			    <td><%= item.getLocalEncontrado()%></td>
			    <td><%= item.getPrazo()%></td>
			    <td>
			   		<form action="control/delete.jsp" method="post">
						<button name="item" value="<%= item.getIdItem()%>" type="submit">Deletar</button>
					</form>
			    </td>
			  </tr>
			  				
			  			<%
			  		}
			  	%>
			</table>
		</div>  
    </body>
</html>
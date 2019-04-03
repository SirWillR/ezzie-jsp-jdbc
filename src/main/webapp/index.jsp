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
    </body>
</html>
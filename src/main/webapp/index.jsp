<%@page language="java" contentType="text/html" pageEncoding="UTF-8" import="model.*, model.dao.*, java.util.List" %>
<html>
    <head>
        <title>ProgWeb</title>
        <meta charset="UTF-8">
		<script type="text/javascript" src="js/script.js"></script>
		<link rel="stylesheet" href="css/style.css">
    </head>
    <body>
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
        <p>
			<% 
				ItemPerdidoDAO itemPerdidoDao = DaoFactory.createItemPerdidoDAO();
			
				ItemPerdido itemPerdido = itemPerdidoDao.findById(1);
				List<ItemPerdido> list = itemPerdidoDao.findAll();

				for (ItemPerdido obj : list) {
					out.print(obj.getNome());
				}
			%>
        </p>
    </body>
</html>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" import="model.*, model.dao.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>ProgWeb</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <p>
			<% 
				ItemPerdidoDAO itemPerdidoDao = DaoFactory.createItemPerdidoDAO();
			
				ItemPerdido itemPerdido = itemPerdidoDao.findById(1);
				
				out.print(itemPerdido.getNome());
			%>
        </p>
    </body>
</html>
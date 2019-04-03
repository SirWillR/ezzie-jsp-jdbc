<%@ page import="model.*, model.dao.*, java.util.List, java.text.DateFormat, java.text.SimpleDateFormat" %>
<html>
<body>
<%
	ItemPerdidoDAO itemPerdidoDao = DaoFactory.createItemPerdidoDAO();
	itemPerdidoDao.deleteById(Integer.parseInt(request.getParameter("item")));
	
%>
<script type="text/javascript">window.location.href = "../index.jsp";</script>
</body>
</html>
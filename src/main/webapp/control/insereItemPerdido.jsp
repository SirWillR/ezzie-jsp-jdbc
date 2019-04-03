<%@ page import="model.*, model.dao.*, java.util.List, java.text.DateFormat, java.text.SimpleDateFormat" %>
<html>
<body>
<%
	ItemPerdidoDAO itemPerdidoDao = DaoFactory.createItemPerdidoDAO();
	TipoDeItemDAO tipoDeItemDao = DaoFactory.createTipoDeItemDAO();
	LocalizacaoDAO localizacaoDao = DaoFactory.createLocalizacaoDAO();
	
	ItemPerdido item = new ItemPerdido();
	
	item.setNomeItem(request.getParameter("nome"));
	
	TipoDeItem tipo = tipoDeItemDao.findById(Integer.parseInt(request.getParameter("tipo")));
	item.setTipo(tipo);
	
	Localizacao local = localizacaoDao.findById(Integer.parseInt(request.getParameter("cidade")));
	item.setLocalizacao(local);
	
	DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
	java.util.Date date = formatter.parse(request.getParameter("date"));
	java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
	item.setData(sqlDate);
	
	item.setLocalEncontrado(request.getParameter("local"));
	item.setPrazo(Integer.parseInt(request.getParameter("prazo")));
	
	item.setPessoaQueAchou(new Usuario(1, "user", "pass", "name", "email@email.com", "00 0000-0000", "facebook/user"));
	
	itemPerdidoDao.insert(item);	
	
%>
<script type="text/javascript">window.location.href = "../index.jsp";</script>
</body>
</html>
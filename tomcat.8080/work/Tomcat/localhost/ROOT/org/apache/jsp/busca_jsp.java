/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.0.M6
 * Generated at: 2019-04-03 20:37:05 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import model.*;
import model.dao.*;

public final class busca_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("model");
    _jspx_imports_packages.add("model.dao");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>ProgWeb</title>\r\n");
      out.write("        <meta charset=\"UTF-8\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"js/script.js\"></script>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("    \t<!--\r\n");
      out.write("\t\t<div class=\"outer\">\r\n");
      out.write("\t\t\t<div class=\"middle\">\r\n");
      out.write("\t\t\t\t<div class=\"inner\">\r\n");
      out.write("\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"loader-bg\"></div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"loader\"></div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t-->\r\n");
      out.write("        <div class=\"container\">  \r\n");
      out.write("\t\t\t<form action=\"control/insereItemPerdido.jsp\" class=\"form\" method=\"post\" tabindex=\"1\">  \r\n");
      out.write("\t\t\t   \t<input type=\"text\" class=\"form-input\" name=\"nome\" placeholder=\"Nome do Item\" autocomplete=\"off\" required />\r\n");
      out.write("\t\t\t\t<select name=\"tipo\" class=\"form-input\" required>\r\n");
      out.write("\t\t\t\t\t<option value=\"\">Selecione o Tipo</option>\r\n");
      out.write("\t\t\t\t \t");

				  		TipoDeItemDAO tipoDoItemDao = DaoFactory.createTipoDeItemDAO();
				  		List<TipoDeItem> listTipos = tipoDoItemDao.findAll();

				  		for(TipoDeItem tipo : listTipos){
				  			
      out.write("\r\n");
      out.write("\t\t\t\t  \t\t\t\t<option value=\"");
      out.print( tipo.getIdTipo());
      out.write('"');
      out.write('>');
      out.print( tipo.getNome());
      out.write("</option>\r\n");
      out.write("\t\t\t\t  \t\t\t");

				  		}
				  	
      out.write("\r\n");
      out.write("\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t<select name=\"cidade\" class=\"form-input\" required>\r\n");
      out.write("\t\t\t\t\t<option value=\"\">Selecione a Cidade</option>\r\n");
      out.write("\t\t\t\t \t");

				  		LocalizacaoDAO localizacaoDao = DaoFactory.createLocalizacaoDAO();
				  		List<Cidade> listCidades = localizacaoDao.findAllCity(11);

				  		for(Cidade cidade : listCidades){
				  			
      out.write("\r\n");
      out.write("\t\t\t\t  \t\t\t\t<option value=\"");
      out.print( cidade.getId());
      out.write('"');
      out.write('>');
      out.print( cidade.getNome());
      out.write("</option>\r\n");
      out.write("\t\t\t\t  \t\t\t");

				  		}
				  	
      out.write("\r\n");
      out.write("\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t<input type=\"date\" class=\"form-input\" name=\"date\" placeholder=\"Data\" required />  \r\n");
      out.write("\t\t\t  \t<input type=\"text\" class=\"form-input\" name=\"local\" placeholder=\"Local Encontrado\" required />  \r\n");
      out.write("\t\t\t \t<select name=\"prazo\" class=\"form-input\" required>\r\n");
      out.write("\t\t\t \t\t<option value=\"\">Selecione o Prazo</option>\r\n");
      out.write("\t\t\t \t\t<option value=\"10\">10</option>\r\n");
      out.write("\t\t\t \t\t<option value=\"10\">15</option>\r\n");
      out.write("\t\t\t \t\t<option value=\"10\">30</option>\r\n");
      out.write("\t\t\t \t\t<option value=\"10\">60</option>\r\n");
      out.write("\t\t\t \t\t<option value=\"10\">90</option>\r\n");
      out.write("\t\t\t \t</select>\r\n");
      out.write("\t\t\t\t<button type=\"submit\" class=\"form-button\">Enviar</button>  \r\n");
      out.write("\t\t\t</form>  \r\n");
      out.write("\t\t</div>  \r\n");
      out.write("    </body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

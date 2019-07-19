package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import model.Usuario;
import connection.DBconnection;
import connection.HbernateUtil;

@WebFilter(urlPatterns = {"/cadastrarObjeto.jsp", "/perfil.jsp"})
public class Filter implements javax.servlet.Filter {

	private static Connection conn;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			Usuario usuario = (Usuario) req.getSession().getAttribute("userSession");
			
			if(usuario == null) {
				RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
				view.forward(request, response);
				return;
			}
			
			// executa as ações do request e response
			chain.doFilter(request, response);
			
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		conn = DBconnection.getConnection();
		HbernateUtil.getEntityManager();
	}
}

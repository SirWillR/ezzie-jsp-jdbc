package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DaoLogin;
import model.dao.DaoUsuario;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoLogin daologin = new DaoLogin();
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		if(acao != null && acao.equalsIgnoreCase("sair")) {
			request.getSession().setAttribute("userSession", null);
		}
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		try {
			if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				Long userId = daologin.validarLogin(login, senha);
				DaoUsuario daoUsuario = new DaoUsuario();
				if(userId != null) {
					request.getSession().setAttribute("userSession", daoUsuario.consultar(String.valueOf(userId)));
					response.getWriter().write("OK");
					return;
				} else {
					response.getWriter().write("Login e/ou Senha inválidos");
				}
			} else {
				response.getWriter().write("Informe o Login e Senha");
			}
			response.setContentType("text/plain");
			response.setCharacterEncoding("ISO-8859-1");
			response.setStatus(200);
		} catch (Exception e) {
			response.setContentType("text/plain");
			response.setCharacterEncoding("ISO-8859-1");
			response.getWriter().write("Falha Interna");
			response.setStatus(500);
			e.printStackTrace();
		}
	}
}

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import model.dao.DaoObjeto;

@WebServlet("/PerfilServlet")
public class PerfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PerfilServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("userSession");
			
			DaoObjeto daoObjeto = new DaoObjeto();
			
			request.setAttribute("objetos", daoObjeto.listar(String.valueOf(usuario.getId())));
			request.setAttribute("user", usuario);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/perfil.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			new ErrorServlet(request, response, e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

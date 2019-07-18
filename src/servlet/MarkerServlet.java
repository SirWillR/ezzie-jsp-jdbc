package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Objeto;
import model.dao.DaoObjeto;

@WebServlet("/MarkerServlet")
public class MarkerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MarkerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cidade = request.getParameter("cidade");
		String uf = request.getParameter("uf");
		String situacao = request.getParameter("situacao");
		String tipo = request.getParameter("tipo");
		
		try {
			DaoObjeto daoObjeto = new DaoObjeto();
			List<Objeto> lista = daoObjeto.listarMarker(cidade, uf, situacao, tipo);
			List<String> listaMarkerList = new ArrayList<String>();
			for (Objeto objeto : lista) {
				Gson gson = new Gson();
				String json = gson.toJson(objeto);
				listaMarkerList.add(json);
			}
			response.getWriter().write(listaMarkerList.toString());
			response.setContentType("text/plain");
			response.setCharacterEncoding("ISO-8859-1");
			response.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

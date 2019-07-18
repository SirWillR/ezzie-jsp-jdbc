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

@WebServlet("/PesquisaServlet")
public class PesquisaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DaoObjeto daoObjeto = new DaoObjeto();
	
    public PesquisaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uf = request.getParameter("uf");
		String cidade = request.getParameter("cidade");
		String situacao = request.getParameter("situacao");
		String type = request.getParameter("type");
		
		try {
			List<String> listaJson = new ArrayList<String>();
			Gson gson = new Gson();
			if(type != null && type.equalsIgnoreCase("estado")) {
				List<Objeto> lista = daoObjeto.listarEstados(situacao);
				for (Objeto objeto : lista) {
					listaJson.add(gson.toJson(objeto));
				}
			} else if(type != null && type.equalsIgnoreCase("cidade")) {
				List<Objeto> lista = daoObjeto.listarCidades(uf, situacao);
				for (Objeto objeto : lista) {
					listaJson.add(gson.toJson(objeto));
				}
			} else if(type != null && type.equalsIgnoreCase("tipo")) {
				List<Objeto> lista = daoObjeto.listarTipos(uf, cidade, situacao);
				for (Objeto objeto : lista) {
					listaJson.add(gson.toJson(objeto));
				}
			}
			response.getWriter().write(listaJson.toString());
			response.setContentType("text/plain");
			response.setCharacterEncoding("ISO-8859-1");
			response.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

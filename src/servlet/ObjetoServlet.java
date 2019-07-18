package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.Objeto;
import model.Usuario;
import model.dao.DaoObjeto;
import model.dao.DaoUsuario;

@WebServlet("/ObjetoServlet")
@MultipartConfig
public class ObjetoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoObjeto daoObjeto = new DaoObjeto();
       
    public ObjetoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		try {
			if(id == null || id.isEmpty()) {
				return;
			}
			DaoUsuario daoUsuario = new DaoUsuario();
			Objeto objeto = daoObjeto.consultar(id);
			Usuario usuario = daoUsuario.consultar(String.valueOf(objeto.getUserId()));
			
			if(objeto == null || usuario == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			request.setAttribute("objeto", objeto);
			request.setAttribute("user", usuario);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/detalhes.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		String tipo = request.getParameter("tipo");
		String situacao = request.getParameter("situacao");
		String descricao = request.getParameter("descricao");
		String cidade = request.getParameter("cidade");
		String uf = request.getParameter("estado");
		String latLng = request.getParameter("latLng");
		
		
		try {
			if(request.getSession().getAttribute("userSession") == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
				return;
			}
			Usuario usuario = (Usuario) request.getSession().getAttribute("userSession");
			Objeto obj = new Objeto();
			obj.setTitulo(titulo);
			obj.setTipo(tipo);
			obj.setSituacao(situacao);
			obj.setDescricao(descricao);
			obj.setCidade(cidade);
			obj.setUf(uf);
			
			if(ServletFileUpload.isMultipartContent(request)) {
				Part imagemFoto = request.getPart("imgInp");
				if(imagemFoto != null && imagemFoto.getInputStream().available() > 0) {
					byte[] bytesImagem = converteStreamParaByte(imagemFoto.getInputStream());
					
					BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytesImagem));
					int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
					
					BufferedImage resizedImage = new BufferedImage(100, 100, type);
					Graphics2D g = resizedImage.createGraphics();
					g.drawImage(bufferedImage, 0, 0, 100, 100, null);
					g.dispose();
					
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(resizedImage, "png", baos);
					
					String fotoBase64Mini = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
					obj.setImagem(fotoBase64Mini);
				}
			}
			
			if(latLng != null && !latLng.isEmpty()) {
				latLng = latLng.replace("(", "{ \"lat\" : ").replace(",", ", \"lng\" : ").replace(")", "}");
			}
			
			obj.setLatLng(latLng);
			obj.setUserId(usuario.getId());
			
			daoObjeto.salvar(obj);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			new ErrorServlet(request, response, e);
		}
	}

	private byte[] converteStreamParaByte(InputStream imagem) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();
		while(reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}
		return baos.toByteArray();
	}
}

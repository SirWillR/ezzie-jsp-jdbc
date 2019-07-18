package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.Usuario;
import model.dao.DaoUsuario;


@WebServlet("/RegisterServlet")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();
	
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("loginR");
		String senha = request.getParameter("senhaR");
		String nome = request.getParameter("nomeR");
		String telefone = request.getParameter("telefoneR");
		String email = request.getParameter("emailR");
		
		try {
			
			Usuario usuario = new Usuario();
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);
			usuario.setEmail(email);
			
			if(ServletFileUpload.isMultipartContent(request)) {
				Part imagemFoto = request.getPart("imageUpload");
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
					usuario.setFotoBase64Mini(fotoBase64Mini);
				}
			}
			
			if(login != null && !login.isEmpty()) {
				if(daoUsuario.verificarLogin(login)) {
					Long userId = daoUsuario.salvar(usuario);
					request.getSession().setAttribute("userSession", daoUsuario.consultar(String.valueOf(userId)));
					response.getWriter().write("OK");
				} else {
					response.getWriter().write("Este usuário já existe");
				}
			}
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("ISO-8859-1");
			response.setStatus(200);
		} catch (Exception e) {
			response.setContentType("text/plain");
			response.setCharacterEncoding("ISO-8859-1");
			response.getWriter().write("Falha Interna");
			response.setStatus(500);
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

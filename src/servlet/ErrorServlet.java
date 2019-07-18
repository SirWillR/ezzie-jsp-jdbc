package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Error")
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ErrorServlet(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException {
    	StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		RequestDispatcher view = request.getRequestDispatcher("/error.jsp");
		request.setAttribute("error", errors.toString().replace("at ", "<br/>at "));
		request.setAttribute("msg", e.toString());
		view.forward(request, response);
    }
}

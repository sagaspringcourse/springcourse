package rs.saga.mvc.controller;

import rs.saga.mvc.model.Player;
import rs.saga.mvc.model.PlayerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(value="/servlet")
public class ServletResponse extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			Player player = new PlayerDAO().findPlayer(id);
			response.setContentType("text/html; charset=ISO-8859-1");
			Writer out =  response.getWriter();

			out.write("\r\n");
			out.write("\r\n");
			out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
			out.write("<html>\r\n");
			out.write("<head>\r\n");
			out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
			out.write("<title>Example</title>\r\n");
			out.write("</head>\r\n");
			out.write("<body>\r\n");
			out.write("\r\n");

			out.write("\t\r\n");
			out.write("\r\n");
			out.write("<h1>Hello ");
			out.write(player.getFirstName());
			out.write("</h1>\r\n");
			out.write("\r\n");
			out.write("</body>\r\n");
			out.write("</html>");
		} catch (Throwable t) {

		}
	}
	
}

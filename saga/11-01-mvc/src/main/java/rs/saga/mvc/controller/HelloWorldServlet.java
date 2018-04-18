package rs.saga.mvc.controller;

import rs.saga.mvc.model.PlayerDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @WebServlet(value="/hello")
public class HelloWorldServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		req.setAttribute("player", new PlayerDAO().findPlayer(id));
		req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);
	}
	
}

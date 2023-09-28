package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProjectService;
@WebServlet(name = "project",urlPatterns = {"/groupwork","/groupwork-add"})
public class ProjectController extends HttpServlet{
	private ProjectService projectService = new ProjectService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		req.setAttribute("listProject", projectService.getListProject());
		switch (path) {
		case "/groupwork":
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
			break;
		case "/groupwork-add":
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
			break;
		default:
			break;
		}
	}
}


package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RoleService;
import service.UserService;
@WebServlet(name = "role",urlPatterns = {"/role","/role-add"})
public class RoleController extends HttpServlet{
	
	private RoleService roleService = new RoleService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		req.setAttribute("listRole", roleService.getListRole());
		switch (path) {
		case "/role":
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
			break;
		case "/role-add":
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			break;
		default:
			break;
		}
	}
}

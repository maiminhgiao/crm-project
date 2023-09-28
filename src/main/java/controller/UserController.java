package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RoleService;
import service.UserService;
@WebServlet(name = "user",urlPatterns = {"/user","/user-add","/user-detail"})
public class UserController extends HttpServlet{
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		req.setAttribute("listUser", userService.getListUsers());
		req.setAttribute("listRole", roleService.getListRole());
		switch (path) {
		case "/user":
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			break;
		case "/user-add":
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			break;
		case "/user-detail":
			req.getRequestDispatcher("user-details.jsp").forward(req, resp);
			break;
		default:
			break;
		}
	}
}

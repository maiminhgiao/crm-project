package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.JobService;
import service.ProjectService;
import service.UserService;
@WebServlet(name = "job",urlPatterns = {"/task","/task-add"})
public class JobController extends HttpServlet{
	private JobService jobService = new JobService();
	private UserService userService = new UserService();
	private ProjectService projectService = new ProjectService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		req.setAttribute("listJob", jobService.getListJob());
		req.setAttribute("listProject", projectService.getListProject());
		req.setAttribute("listUser", userService.getListUsers());
		
		switch (path) {
		case "/task":
			req.getRequestDispatcher("task.jsp").forward(req, resp);
			break;
		case "/task-add":
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
			break;
		default:
			break;
		}
	}
}
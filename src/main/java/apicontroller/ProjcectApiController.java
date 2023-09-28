package apicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import entity.Project;
import entity.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import payload.response.BaseResponse;
import service.ProjectService;
import service.RoleService;

@WebServlet(name = "ProjectApi", urlPatterns = { "/api/groupwork","/api/groupwork/add","/api/groupwork/delete","/api/groupwork/update","/api/groupwork/groupwork" })
public class ProjcectApiController extends HttpServlet {
	private ProjectService projectService = new ProjectService();
	private BaseResponse response = new BaseResponse();
	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		List<Project> listProjects = projectService.getListProject();
		response.setStatusCode(200);
		response.setMessage("");
		response.setData(listProjects);

		String data = gson.toJson(response);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		out.print(data);
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		PrintWriter out = resp.getWriter();
		String data;
		boolean flag;
		switch (path) {
		case "/api/groupwork/add":
			Project projectAdd = new Project();
			projectAdd.setName(req.getParameter("name"));
			projectAdd.setStartDate(req.getParameter("startDate"));
			projectAdd.setEndDate(req.getParameter("endDate"));
			flag = projectService.addProject(projectAdd);

			response.setStatusCode(200);
			response.setMessage(flag ? "Thêm Thành công" : "Thêm Thất Bại");
			response.setData(flag);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/groupwork/delete":
			int idDelete = Integer.parseInt(req.getParameter("id"));
			flag = projectService.deleteProject(idDelete);

			response.setStatusCode(200);
			response.setMessage(flag ? "Xóa Thành công" : "Xóa Thất Bại");
			response.setData(flag);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/groupwork/update":
			Project projectUpdate = new Project();
			projectUpdate.setId(Integer.parseInt(req.getParameter("id")));
			projectUpdate.setName(req.getParameter("name"));
			projectUpdate.setStartDate(req.getParameter("startDate"));
			projectUpdate.setEndDate(req.getParameter("endDate"));
			flag = projectService.updateProject(projectUpdate);

			response.setStatusCode(200);
			response.setMessage(flag ? "đã sửa thành công" : "thất bại rồi");
			response.setData(flag);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/groupwork/groupwork":
			int id = Integer.parseInt(req.getParameter("id"));
			Project project = projectService.getProject(id);

			response.setStatusCode(200);
			response.setMessage("");
			response.setData(project);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;

		default:
			break;
		}
	}
}

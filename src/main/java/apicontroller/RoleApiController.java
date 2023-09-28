package apicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import entity.Role;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import payload.response.BaseResponse;
import service.RoleService;

@WebServlet(name = "roleApi", urlPatterns = { "/api/role", "/api/role/add", "/api/role/delete", "/api/role/update",
		"/api/role/role" })
public class RoleApiController extends HttpServlet {
	private RoleService roleService = new RoleService();
	private BaseResponse response = new BaseResponse();
	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		List<Role> listRoles = roleService.getListRole();
		response.setStatusCode(200);
		response.setMessage("");
		response.setData(listRoles);

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
		case "/api/role/add":
			Role roleAdd = new Role();
			roleAdd.setName(req.getParameter("name"));
			roleAdd.setDescription(req.getParameter("desc"));
			flag = roleService.addRole(roleAdd);

			response.setStatusCode(200);
			response.setMessage(flag ? "Thêm Thành công" : "Thêm Thất Bại");
			response.setData(flag);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/role/delete":
			int idDelete = Integer.parseInt(req.getParameter("id"));
			flag = roleService.deleteRole(idDelete);

			response.setStatusCode(200);
			response.setMessage(flag ? "Xóa Thành công" : "Xóa Thất Bại");
			response.setData(flag);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/role/update":
			Role roleUpdate = new Role();
			roleUpdate.setId(Integer.parseInt(req.getParameter("id")));
			roleUpdate.setName(req.getParameter("name"));
			roleUpdate.setDescription(req.getParameter("desc"));

			flag = roleService.updateRole(roleUpdate);

			response.setStatusCode(200);
			response.setMessage(flag ? "Sửa Thành công" : "Sửa Thất Bại");
			response.setData(flag);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/role/role":
			int id = Integer.parseInt(req.getParameter("id"));
			Role role = roleService.getRole(id);

			response.setStatusCode(200);
			response.setMessage("");
			response.setData(role);

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

package apicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import payload.response.BaseResponse;
import service.UserService;

@WebServlet(name = "userApi", urlPatterns = { "/api/user", "/api/user/add", "/api/user/delete", "/api/user/update",
		"/api/user/user" })
public class UserApiController extends HttpServlet {
	private UserService userService = new UserService();
	private Gson gson = new Gson();
	private BaseResponse response = new BaseResponse();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		List<User> listUsers = userService.getListUsers();
		response.setStatusCode(200);
		response.setMessage("");
		response.setData(listUsers);

		String data = gson.toJson(response);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		out.print(data);
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		PrintWriter out = resp.getWriter();
		String data;
		boolean flag;
		switch (path) {
		case "/api/user/add":
			User userAdd = new User();
			userAdd.setFullName(req.getParameter("fullName").trim());
			userAdd.setEmail(req.getParameter("email"));
			userAdd.setPwd(req.getParameter("pwd"));
			userAdd.setPhone(req.getParameter("phone"));
			userAdd.setIdRole(Integer.parseInt(req.getParameter("idRole")));
			
			flag = userService.addUser(userAdd);

			response.setStatusCode(200);
			response.setMessage(flag ? "Thêm Thành công" : "Thêm Thất Bại");
			response.setData(flag);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/user/delete":
			int idDelete = Integer.parseInt(req.getParameter("id"));
			flag = userService.deleteUser(idDelete);

			response.setStatusCode(200);
			response.setMessage(flag ? "XÓa Thành công" : "Xóa Thất Bại");
			response.setData(flag);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/user/update":
			User userUpdate = new User();
			userUpdate.setId(Integer.parseInt(req.getParameter("id")));
			userUpdate.setFullName(req.getParameter("fullName").trim());
			userUpdate.setEmail(req.getParameter("email"));
			userUpdate.setPwd(req.getParameter("pwd"));
			userUpdate.setPhone(req.getParameter("phone"));
			userUpdate.setIdRole(Integer.parseInt(req.getParameter("idRole")));
			
			flag = userService.updateUser(userUpdate);

			response.setStatusCode(200);
			response.setMessage(flag ? "Sửa Thành công" : "Sửa Thất Bại");
			response.setData(flag);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/user/user":
			int id = Integer.parseInt(req.getParameter("id"));
			User getUser = userService.getUser(id);

			response.setStatusCode(200);
			response.setMessage("");
			response.setData(getUser);

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

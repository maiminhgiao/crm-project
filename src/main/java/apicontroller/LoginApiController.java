package apicontroller;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import payload.response.BaseResponse;
import service.LoginService;

@WebServlet(name = "loginApi", urlPatterns = { "/api/login" })
public class LoginApiController extends HttpServlet {
	private LoginService loginService = new LoginService();
	private BaseResponse baseResponse = new BaseResponse();
	private Gson gson = new Gson();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		boolean flag = loginService.getLogin(email, password);

		baseResponse.setStatusCode(200);
		baseResponse.setMessage(flag ? "Đăng nhập Thành công" : "Đăng nhập Thất bại");
		baseResponse.setData(flag);

		String data = gson.toJson(baseResponse);
		PrintWriter out = resp.getWriter();
		
		resp.setContentType("/application/json");
		resp.setCharacterEncoding("UTF-8");

		out.print(data);
		out.flush();
	}
}

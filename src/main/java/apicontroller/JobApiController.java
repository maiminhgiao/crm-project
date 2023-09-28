package apicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import entity.Job;
import entity.Project;
import entity.Status;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import payload.response.BaseResponse;
import service.JobService;
import service.UserService;
@WebServlet(name = "jobApi",urlPatterns = {"/api/task","/api/task/add","/api/task/delete","/api/task/update","/api/task/task"})
public class JobApiController extends HttpServlet{
	private JobService jobService = new JobService();
	private UserService userService = new UserService();
	private BaseResponse response = new BaseResponse();
	private Gson gson = new Gson();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		List<Job> listJobs = jobService.getListJob();
		response.setStatusCode(200);
		response.setMessage("");
		response.setData(listJobs);

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
		case "/api/task/add":
			Job jobAdd = new Job();
			jobAdd.setName(req.getParameter("nameJob"));
			jobAdd.setStartDate(req.getParameter("startDate"));
			jobAdd.setEndDate(req.getParameter("endDate"));
			jobAdd.setIdProject(Integer.parseInt(req.getParameter("idProject")));

			User userAdd = new User();
			userAdd.setId(Integer.parseInt(req.getParameter("idUser")));
			jobAdd.setUser(userAdd);
			
			Status statusAdd = new Status();
			statusAdd.setId(Integer.parseInt(req.getParameter("idStatus")));
			jobAdd.setStatus(statusAdd);
			
			flag = jobService.addJob(jobAdd);

			response.setStatusCode(200);
			response.setMessage("");
			response.setData(flag);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/task/delete":
			int idDelete = Integer.parseInt(req.getParameter("id"));
			flag = jobService.deleteJob(idDelete);

			response.setStatusCode(200);
			response.setMessage(flag ? "Xóa Thành Công":"Xóa Thất Bại");
			response.setData(flag);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/task/update":
			Job jobUpdate = new Job();
			jobUpdate.setId(Integer.parseInt(req.getParameter("idJob")));
			jobUpdate.setName(req.getParameter("nameJob"));
			jobUpdate.setStartDate(req.getParameter("startDate"));
			jobUpdate.setEndDate(req.getParameter("endDate"));
			jobUpdate.setIdProject(Integer.parseInt(req.getParameter("idProject")));

			User userUpdate = new User();
			userUpdate.setId(Integer.parseInt(req.getParameter("idUser")));
			jobUpdate.setUser(userUpdate);
			
			Status statusUpdate = new Status();
			statusUpdate.setId(Integer.parseInt(req.getParameter("idStatus")));
			jobUpdate.setStatus(statusUpdate);
			
			flag = jobService.updateJob(jobUpdate);

			response.setStatusCode(200);
			response.setMessage("");
			response.setData(flag);

			data = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(data);
			out.flush();
			break;
		case "/api/task/task":
			int id = Integer.parseInt(req.getParameter("id"));
			Job job = jobService.getJob(id);
			
			response.setStatusCode(200);
			response.setMessage("");
			response.setData(job);
			
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

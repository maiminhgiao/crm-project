package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.Config;
import entity.Job;
import entity.Project;
import entity.Status;
import entity.User;

public class JobRepository {
	public List<Job> getListJob() {
		String query = "SELECT j.*,u.fullName,p.name,s.name FROM Job_Status_Users jsu\r\n"
				+ "JOIN Job j ON jsu.id_job = j.id\r\n"
				+ "JOIN Users u ON jsu.id_user = u.id\r\n"
				+ "JOIN Status s ON jsu.id_status = s.id\r\n"
				+ "JOIN Project p ON j.id_project = p.id;";
		Connection connection = Config.getConnection();
		List<Job> list = new ArrayList<Job>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				
				Job job = new Job();
				job.setId(resultSet.getInt("id"));
				job.setName(resultSet.getString("j.name"));
				job.setStartDate(resultSet.getString("startDate"));
				job.setEndDate(resultSet.getString("endDate"));
				job.setIdProject(resultSet.getInt("id_project"));
				
				User user = new User();
				user.setFullName(resultSet.getString("fullName"));
				job.setUser(user);
				
				Project project = new Project();
				project.setName(resultSet.getString("p.name"));
				job.setProject(project);
				
				Status status = new Status();
				status.setName(resultSet.getString("s.name"));
				job.setStatus(status);
				
				list.add(job);
				

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.getLocalizedMessage());
			}
		}
		return list;
	}

	public int addJob(Job job) {
		String query = "START TRANSACTION;"
				+ "INSERT INTO Job(name, startDate, endDate, id_project) VALUES (?, ?, ?, ?);\r\n"
				+ "INSERT INTO Job_Status_Users(id_user, id_status, id_job) VALUES (?, ?, last_insert_id());\r\n"
				+ "COMMIT;";
		Connection connection = Config.getConnection();
		int n = -1;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, job.getName());
			preparedStatement.setString(2, job.getStartDate());
			preparedStatement.setString(3, job.getEndDate());
			preparedStatement.setInt(4, job.getIdProject());
			
			preparedStatement.setInt(5, job.getUser().getId());
			preparedStatement.setInt(6, job.getIdProject());
			
			preparedStatement.execute();
			
			n = preparedStatement.getUpdateCount();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.getLocalizedMessage());
			}
		}
		return n;
	}
	

	public int deleteJob(int id) {
		String query = ""
				+ "DELETE jsu.*,j.*,pu.*\r\n"
				+ "FROM Job_Status_Users jsu\r\n"
				+ "         JOIN Job j ON jsu.id_job = j.id\r\n"
				+ "         JOIN Users u ON jsu.id_user = u.id\r\n"
				+ "         JOIN Status s ON jsu.id_status = s.id\r\n"
				+ "         JOIN Project p ON j.id_project = p.id\r\n"
				+ "         JOIN Project_Users pu ON j.id_project = pu.id_project\r\n"
				+ "WHERE jsu.id_job = j.id AND pu.id_user = jsu.id_user\r\n"
				+ "        AND j.id_project = pu.id_project\r\n"
				+ "        AND j.id = ?;"
				+ "";
		Connection connection = Config.getConnection();
		int n = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			n = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.getLocalizedMessage());
			}
		}
		return n;
	}
	
	/*
	 * Query :		
	 * 				SELECT j.*, u.id, u.fullName, p.id, p.name, s.id, s.name
					FROM Job_Status_Users jsu
         			JOIN Job j ON jsu.id_job = j.id
         			JOIN Users u ON jsu.id_user = u.id
         			JOIN Status s ON jsu.id_status = s.id
         			JOIN Project p ON j.id_project = p.id;
	 */
	public int updateJob(Job job) {
		String query = "START TRANSACTION;\r\n"
				+ "    UPDATE Job\r\n"
				+ "        SET name = ?, startDate = ?, endDate = ?,id_project = ? WHERE id = ?;\r\n"
				+ "    UPDATE Job_Status_Users\r\n"
				+ "        SET id_user = ?, id_status = ? WHERE id_job = ?;\r\n"
				+ "COMMIT;";
		
		Connection connection = Config.getConnection();
		int n = -1;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, job.getName());
			preparedStatement.setString(2, job.getStartDate());
			preparedStatement.setString(3, job.getEndDate());
			preparedStatement.setInt(4, job.getIdProject());
			preparedStatement.setInt(5, job.getId());
			
			preparedStatement.setInt(6, job.getUser().getId());
			preparedStatement.setInt(7, job.getStatus().getId());
			preparedStatement.setInt(8, job.getId());

			preparedStatement.execute();

			n = preparedStatement.getUpdateCount();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.getLocalizedMessage());
			}
		}
		return n;
	}

	public Job getJob(int id) {
		String query = "SELECT j.*,u.id,u.fullName,p.id,p.name,s.id,s.name\r\n"
				+ "FROM Job_Status_Users jsu\r\n"
				+ "         JOIN Job j ON jsu.id_job = j.id\r\n"
				+ "         JOIN Users u ON jsu.id_user = u.id\r\n"
				+ "         JOIN Status s ON jsu.id_status = s.id\r\n"
				+ "         JOIN Project p ON j.id_project = p.id\r\n"
				+ "WHERE j.id = ?";
		Connection connection = Config.getConnection();
		Job job = new Job();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				job.setId(resultSet.getInt("j.id"));
				job.setName(resultSet.getString("j.name"));
				job.setStartDate(resultSet.getString("startDate"));
				job.setEndDate(resultSet.getString("endDate"));
				job.setIdProject(resultSet.getInt("id_project"));

				User user = new User();
				user.setId(resultSet.getInt("u.id"));
				user.setFullName(resultSet.getString("fullName"));
				job.setUser(user);

				Project project = new Project();
				project.setId(resultSet.getInt("p.id"));
				project.setName(resultSet.getString("p.name"));
				job.setProject(project);

				Status status = new Status();
				status.setId(resultSet.getInt("s.id"));
				status.setName(resultSet.getString("s.name"));
				job.setStatus(status);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.getLocalizedMessage());
			}
		}
		return job;

	}
}
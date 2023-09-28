package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.Config;
import entity.Project;
import entity.Role;

public class ProjectRepository {
	public List<Project> getListProject() {
		String query = "SELECT * FROM Project;";
		Connection connection = Config.getConnection();
		List<Project> list = new ArrayList<Project>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Project project = new Project();
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getString("startDate"));
				project.setEndDate(resultSet.getString("endDate"));

				list.add(project);

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

	public int addProject(Project project) {
		String query = "INSERT INTO Project(name, startDate, endDate) VALUES (?,?,?);";
		Connection connection = Config.getConnection();
		int n = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, project.getName());
			preparedStatement.setString(2, project.getStartDate());
			preparedStatement.setString(3, project.getEndDate());

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

	public int deleteProject(int id) {
		String query = "DELETE FROM Project where id = ?";
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

	public int updateProject(Project project) {
		String query = "UPDATE Project SET name = ?, startDate = ?, endDate = ? WHERE id = ?";
		Connection connection = Config.getConnection();
		int n = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, project.getName());
			preparedStatement.setString(2, project.getStartDate());
			preparedStatement.setString(3, project.getEndDate());
			preparedStatement.setInt(4, project.getId());

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

	public Project getProject(int id) {
		String query = "SELECT * FROM Project WHERE id = ?;";
		Connection connection = Config.getConnection();
		Project project = new Project();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getString("startDate"));
				project.setEndDate(resultSet.getString("endDate"));
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
		return project;

	}
}

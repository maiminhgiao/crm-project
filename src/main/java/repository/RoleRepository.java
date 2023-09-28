package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.Config;
import entity.Role;

public class RoleRepository { 
	public List<Role> getListRole() {
		String query = "SELECT * FROM Role";
		Connection connection = Config.getConnection();
		List<Role> list = new ArrayList<Role>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
				list.add(role);
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
	public int addRole(Role role) {
		String query = "INSERT INTO Role(name, description) VALUES (?,?)";
		Connection connection = Config.getConnection();
		int n = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, role.getName());
			preparedStatement.setString(2, role.getDescription());
			
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
	public int deleteRole(int id) {
		String query = "DELETE FROM Role where id = ?";
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
	
	public int updateRole(Role role) {
		String query = "UPDATE Role SET name = ?,description = ? WHERE id = ?";
		Connection connection = Config.getConnection();
		int n = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, role.getName());
			preparedStatement.setString(2, role.getDescription());
			preparedStatement.setInt(3, role.getId());
			
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
	
	public Role getRole(int id) {
		String query = "SELECT * FROM Role WHERE id = ?;";
		Connection connection = Config.getConnection();
		Role role = new Role();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));				
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
		return role;
	
	}
}

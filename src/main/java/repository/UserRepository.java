package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.Config;
import entity.Job;
import entity.Role;
import entity.User;

public class UserRepository {
	public List<User> getListUsers(){
		String query = "SELECT * FROM Users u JOIN Role r ON r.id = u.id_role";
		Connection connection = Config.getConnection();
		List<User> list = new ArrayList<User>();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setIdRole(resultSet.getInt("id_role"));
				user.setId(resultSet.getInt("id"));

				user.setEmail(resultSet.getString("email"));
				user.setUserName(resultSet.getString("userName"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setFullName(resultSet.getString("fullName"));
				user.setImage(resultSet.getString("image"));
				user.setLastName(resultSet.getString("lastName"));
				user.setPhone(resultSet.getString("phone"));
				user.setPwd(resultSet.getString("pwd"));
				
				Role role = new Role();
				role.setName(resultSet.getString("name"));
				user.setRole(role);
				
				list.add(user);
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
	public User getUsers(int id) {
		String query = "SELECT * FROM Users u JOIN Role r ON u.id_role = r.id WHERE u.id = ?";
		Connection connection = Config.getConnection();
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setIdRole(resultSet.getInt("id_role"));

				user.setEmail(resultSet.getString("email"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setFullName(resultSet.getString("fullName"));
				user.setImage(resultSet.getString("image"));
				user.setLastName(resultSet.getString("lastName"));
				user.setPhone(resultSet.getString("phone"));
				user.setPwd(resultSet.getString("pwd"));
				user.setUserName(resultSet.getString("userName"));

				Role role = new Role();
				role.setName(resultSet.getString("name"));
				user.setRole(role);

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
		return user;
	}
	public int addUser(User user) {
		String query = "INSERT INTO Users(email, pwd, firstName, lastName, fullName, 		userName, phone, id_role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = Config.getConnection();
		int n = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPwd());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getFullName());
			statement.setString(6, user.getUserName());
			statement.setString(7, user.getPhone());
			statement.setInt(8, user.getIdRole());

			n = statement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	public int deleteUser(int id) {
		String query = "DELETE FROM Users WHERE id = ?";
		Connection connection = Config.getConnection();
		int n = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			n = statement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	public int updateUser(User user) {
		String query = "UPDATE Users SET email = ?, pwd = ?, firstName = ?, lastName = ?, fullName = ?, userName = ?, phone = ?, id_role = ? WHERE id = ?";
		Connection connection = Config.getConnection();
		int n = 0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPwd());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getFullName());
			statement.setString(6, user.getUserName());
			statement.setString(7, user.getPhone());
			statement.setInt(8, user.getIdRole());
			statement.setInt(9, user.getId());

			n = statement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

	
}

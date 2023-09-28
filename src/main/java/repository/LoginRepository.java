package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.Config;

public class LoginRepository {
	public boolean getLogin(String email, String password) {
		String query = "SELECT u.id FROM Users u WHERE email = ? AND pwd = ?;";
		Connection connection = Config.getConnection();
		boolean flag = false;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);

			ResultSet resultSet = statement.executeQuery();
			flag = resultSet.next();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getLocalizedMessage());
			}
		}
		return flag;
	}
}

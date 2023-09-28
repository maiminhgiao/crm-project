package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Config {
	private final static String url = "jdbc:mysql://localhost:3312/crm?allowMultiQueries=true";
	private final static String user = "root";
	private final static String pass = "1234";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, pass);
			return connection;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}
}

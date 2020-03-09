package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	@SuppressWarnings("finally")
	public static Connection getConnection (String DB_DRIVER, String DB_URL, String DB_USERNAME, String DB_PASSWORD) {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER).newInstance();
			dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return dbConnection;
		}
	}
}

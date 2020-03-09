package FuncTests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.DatabaseConnection;

public class TargetDatabaseConnectionTest extends DatabaseConnection {
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@ondora04.hu.nl:8521/educ31";
	private static final String DB_USERNAME = "VBMG";
	private static final String DB_PASSWORD = "testtest";
	
	public static void main(String[] args) {
		try {
			Class.forName(DB_DRIVER).newInstance();
			Connection conn;
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME,DB_PASSWORD);
		
			Statement stmt = conn.createStatement();		
			String queryText = "SELECT 1 FROM dual";
			ResultSet rs = stmt.executeQuery(queryText);
			rs.close();
			stmt.close();
			conn.close();
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

}

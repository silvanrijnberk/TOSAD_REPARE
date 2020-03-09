package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConstraintDAO extends DatabaseConnection {

	//Execute the Script to the targetDatabase.
	public void executeScriptToTargetDB(String constraint) {
		
		try (Connection connection = super.getConnection(TargetDatabaseConnection.getDbDriver(), TargetDatabaseConnection.getDbUrl(), TargetDatabaseConnection.getDbUsername(), TargetDatabaseConnection.getDbPassword())){
			Statement statement = connection.createStatement();
			
			statement.executeQuery(constraint);
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

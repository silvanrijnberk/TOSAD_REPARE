package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestToolDatabaseConnection {
	public static Connection initializeDatabase() 
	        throws SQLException, ClassNotFoundException 
	    { 
	        // Initialize all the information regarding 
	        // Database Connection 
	        String dbDriver = "oracle.jdbc.driver.OracleDriver"; 
	        String dbURL = "jdbc:oracle:thin:@ondora04.hu.nl:8521/educ31"; 
	        // Database name to access 
	        String dbUsername = "tosad"; 
	        String dbPassword = "testtest"; 
	  
	        Class.forName(dbDriver); 
	        Connection con = DriverManager.getConnection(dbURL, 
	                                                     dbUsername,  
	                                                     dbPassword); 
	        return con; 
	    } 
	} 


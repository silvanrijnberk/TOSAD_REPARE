package DAO;

public class TargetDatabaseConnection {
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@ondora04.hu.nl:8521/educ31";
	private static final String DB_USERNAME = "VBMG";
	private static final String DB_PASSWORD = "testtest";

	public static String getDbDriver() {
		return DB_DRIVER;
	}
	public static String getDbUrl() {
		return DB_URL;
	}
	public static String getDbUsername() {
		return DB_USERNAME;
	}
	public static String getDbPassword() {
		return DB_PASSWORD;
	}
	
	
	
	
	
	
}


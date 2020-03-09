package DAO;

import BusinessRules.Interentitycomparerule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InterEntityCompareRuleDAO extends DatabaseConnection {
	
	public boolean createInterEntityCompareRule(Interentitycomparerule rule) {
		String query = createQuery(rule);
		boolean succes = false;

		try (Connection connection = super.getConnection(ToolDatabaseConnection.getDbDriver(), ToolDatabaseConnection.getDbUrl(), ToolDatabaseConnection.getDbUsername(), ToolDatabaseConnection.getDbPassword())) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, rule.getScriptCode());
			statement.setString(2, rule.getBusinessRulename());
			statement.setString(3, rule.getOperator());
			statement.setString(4, rule.getColumnValue());
			statement.setString(5, rule.getSecondColumnValue());
			statement.setString(6, rule.getTableValue());
			statement.setString(7, rule.getSecondTableValue());
			statement.setString(8, rule.getException());

			if (statement.executeUpdate() == 1) { // 1 row updated!
				succes = true;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return succes;
	}
	
	public String createQuery(Interentitycomparerule rule) {
		String result = "INSERT INTO INTERENTITYCOMPARERULE (scriptcode, businessRuleName, ruleType, operator, firstColumnValue, secondColumnValue, firstTableValue, secondTableValue, exception) VALUES (?, ?, 'ICMP', ?, ?, ?, ?, ?, ?)";
		return result;
	}
	
	public void updateInterEntityCompareRule(String script, int ruleID) {
		String query = "UPDATE INTERENTITYCOMPARERULE set SCRIPTCODE = ? WHERE RULEID = ?";
	
		try (Connection connection = super.getConnection(ToolDatabaseConnection.getDbDriver(), ToolDatabaseConnection.getDbUrl(), ToolDatabaseConnection.getDbUsername(), ToolDatabaseConnection.getDbPassword())) {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1,  script);
			statement.setInt(2, ruleID);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//Execute the Script to the targetDatabase.
	public boolean executeScriptToTargetDB(String script) {
		boolean result = false;
		try (Connection connection = super.getConnection(TargetDatabaseConnection.getDbDriver(), TargetDatabaseConnection.getDbUrl(), TargetDatabaseConnection.getDbUsername(), TargetDatabaseConnection.getDbPassword())){
			Statement statement = connection.createStatement();
			
			result = statement.execute(script);
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}

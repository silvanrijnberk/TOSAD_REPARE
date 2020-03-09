package DAO;

import BusinessRules.Tuplecomparerule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TupleCompareRuleDAO extends DatabaseConnection {
	
	public boolean createTupleCompareRule(Tuplecomparerule rule) {
		String query = createQuery(rule);
		boolean succes = false;

		try (Connection connection = super.getConnection(ToolDatabaseConnection.getDbDriver(), ToolDatabaseConnection.getDbUrl(), ToolDatabaseConnection.getDbUsername(), ToolDatabaseConnection.getDbPassword())) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, rule.getScriptCode());
			statement.setString(2, rule.getBusinessRulename());
			statement.setString(3, rule.getOperator());
			statement.setString(4, rule.getColumnValue());
			statement.setString(5, rule.getSecondcolumnvalue());
			statement.setString(6, rule.getTableValue());
			statement.setString(7, rule.getException());

			if (statement.executeUpdate() == 1) { // 1 row updated!
				succes = true;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return succes;
	}
	
	public String createQuery(Tuplecomparerule rule) {
		String result = "INSERT INTO TUPLECOMPARERULE (scriptcode, businessRuleName, ruleType, operator, firstColumnValue, secondColumnValue, tableValue, exception) VALUES (?, ?, 'TCMP', ?, ?, ?, ?, ?)";
		return result;
	}
	
	public void updateTupleCompareRule(String script, int ruleID) {
		String query = "UPDATE TUPLECOMPARERULE set SCRIPTCODE = ? WHERE RULEID = ?";
		
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
